// Cycling loading screen paragraph
var paragraphs = document.querySelectorAll('.paragraph');
var currentIndex = 0;

function cycleParagraphs() {
    var currentPara = paragraphs[currentIndex];
    var nextIndex = currentIndex + 1;

    // If nextIndex is within bounds, proceed
    if (nextIndex < paragraphs.length) {
        var nextPara = paragraphs[nextIndex];

        // Fade out the current paragraph
        currentPara.classList.remove('show');

        // Wait for the transition to complete
        setTimeout(function () {
            // Hide the current paragraph
            currentPara.style.display = 'none';

            // Show the next paragraph
            nextPara.style.display = 'block';

            // Trigger reflow to restart CSS animation
            nextPara.offsetWidth; // Reading offsetWidth forces a reflow

            // Fade in the next paragraph
            nextPara.classList.add('show');

            // Update currentIndex for the next cycle
            currentIndex = nextIndex;

            // Continue cycling if there are more paragraphs
            if (nextIndex < paragraphs.length - 1) {
                setTimeout(cycleParagraphs, 2000); // Adjust the delay as needed
            } else {
                // Closing the loading screen
                closeLoadingScreen();
            }
        }, 2000); // This timeout should match the CSS transition duration
    }
}

// Initialize all paragraphs except the first one to be hidden
for (var i = 1; i < paragraphs.length; i++) {
    paragraphs[i].style.display = 'none';
}

// Start the cycle
setTimeout(cycleParagraphs, 2000); // Initial delay before starting the cycle

// Close loading screen
function closeLoadingScreen() {
    var navbar = document.querySelector('.navbar');
    var loadingScreen = document.querySelector('.loading-screen');
    loadingScreen.style.setProperty('display', 'none', 'important');
    navbar.style.display = 'flex';
}
