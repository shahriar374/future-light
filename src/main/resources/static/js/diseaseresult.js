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


// Chat js data
Chart.defaults.color = '#fff';
const ctx = document.getElementById('myChart');
const diseaseData = document.querySelectorAll('.disease-data');

new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Heart Attack', 'Kideny Failure', 'Cancer', 'Diabetes', 'Stroke', 'Arthritis', 'Alzheimers'],
        datasets: [{
            label: 'Predicted Disease',
            data: [
                diseaseData[0].value,
                diseaseData[1].value,
                diseaseData[2].value,
                diseaseData[3].value,
                diseaseData[4].value,
                diseaseData[5].value,
                diseaseData[6].value
            ],
            borderWidth: 1,
            borderColor: '#06a1b4',
            backgroundColor: '#32ee88'
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});