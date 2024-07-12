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