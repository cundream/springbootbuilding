'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();
    const token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDcyODA2Mzc3NjYxMDA5OTIwIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE2OTI3ODQ3NDAsInJvbGVzIjpbIueuoeeQhuWRmCJdLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoicGFnZTp0ZXN0In0seyJhdXRob3JpdHkiOiJidG46dGVzdDpxdWVyeSJ9LHsiYXV0aG9yaXR5IjoiYnRuOnRlc3Q6aW5zZXJ0In0seyJhdXRob3JpdHkiOiJwYWdlOm1vbml0b3I6b25saW5lIn0seyJhdXRob3JpdHkiOiJidG46bW9uaXRvcjpvbmxpbmU6cXVlcnkifSx7ImF1dGhvcml0eSI6ImJ0bjptb25pdG9yOm9ubGluZTpraWNrb3V0In0seyJhdXRob3JpdHkiOiJidG46cGFnZTphbGwifV0sImV4cCI6MTY5Mjc4NjU0MH0.-c2FA_ons9QuTzwgcR8LYNxYPDK7Vyynh2cZp7tg1aE";

    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('https://localhost:18082/bootbuliding/ws',token);
        stompClient = Stomp.over(socket);

        stompClient.connect({
            username:username

        }, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {

    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    //额外订阅了mike频道,暂时当做自己的频道,别人需要特意往这个频道发送消息,才能完成 ‘单对单’
    stompClient.subscribe('/user/topic/msg', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN',to:'all'}
        )
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT',
            to:'all'

        };
        console.log("-sss---- ："+ messageInput.value);
        console.log(stompClient);
        console.log(JSON.stringify(chatMessage));
        stompClient.send("/app/chat.sendMessageTest", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' 上线~!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' 离线了!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode('消息：'+message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)