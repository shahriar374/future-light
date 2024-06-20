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
        xhr.onreadystatechange = function() {
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
    userMessage.classList.add('message', 'user-message', 'btn', 'btn-secondary');
    userMessage.innerText = userInput;
    document.getElementById('chatContainer').appendChild(userMessage);
    document.getElementById('userInput').value = '';
}

function displayBotMessage(response) {
    const botMessage = document.createElement('div');
    botMessage.classList.add('message', 'bot-message', 'btn', 'btn-success');
    botMessage.innerText = response.message;
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