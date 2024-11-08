function sendMessage() {
    const userInput = document.getElementById('userInput').value;
    if (userInput.trim() !== '') {
        // Showing user message in the chat box
        displayUserMessage(userInput);

        scrollToBottom();

        // Sending POST request to the server
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/chat", true);

        var csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute("content");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);

        // Response recieved from the server
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var response = JSON.parse(xhr.responseText);
                displayBotMessage(response);

                scrollToBottom();

            }
        };

        var data = JSON.stringify({
            "message": userInput
        });
        xhr.send(data);

    }
}

function displayUserMessage(userInput) {
    const userMessage = document.createElement('div');
    userMessage.classList.add('message', 'user-message');
    userMessage.innerText = userInput;
    document.getElementById('chatContainer').appendChild(userMessage);
    document.getElementById('userInput').value = '';
}

function displayBotMessage(response) {
    const botMessage = document.createElement('div');
    botMessage.classList.add('message', 'bot-message');
    botMessage.innerHTML = response.message;
    document.getElementById('chatContainer').appendChild(botMessage);
}

function scrollToBottom() {
    const chatContainer = document.getElementById('chatContainer');
    chatContainer.scrollTop = chatContainer.scrollHeight;
}

function handleKeyPress(event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
}

function deleteChat() {
    const messages = document.querySelectorAll('.message');

    for (let i = 0; i < messages.length; i++) {
        messages[i].remove();
    }

    var csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute("content");

    fetch(`${window.location.origin}/chat`, {
        method: 'DELETE',
        headers: {
            'X-CSRF-TOKEN': csrfToken,
            'Content-Type': 'application/json'
        },
    })    

}

// Scroll to bottom of the chat box when the page is loaded
window.onload = scrollToBottom();