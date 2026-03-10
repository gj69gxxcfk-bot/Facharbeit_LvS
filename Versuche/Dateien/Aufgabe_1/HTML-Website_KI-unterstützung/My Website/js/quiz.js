/* quiz.js - logic for the "Quiz des Tages" page
   You'll be writing most of the code yourself here.  
   Follow the numbered comments and read the explanations
   so you understand what each piece does.     
*/

// 1. We need a place to remember what answer the user clicked
//    The keys will be question numbers (1..5) and the values
//    will be the index of the chosen answer (0..3).
const userAnswers = {};

// 2. Tell the script which answer is correct for each question.
//    These numbers correspond to the `data-answer` values in HTML.
const correctAnswers = {
    1: 1, // Frage 1: richtige Option ist B, data-answer="1"
    2: 3, // Frage 2: richtige Option ist D (unsinnigerweise)
    3: 0, // Frage 3: richtige Option ist A
    4: 3, // Frage 4: richtige Option ist D
    5: 2  // Frage 5: richtige Option ist C
};

// 3. Find every button with the class .answer-btn and attach a click handler.
//    When the user clicks, we store the choice and highlight the button.
const buttons = document.querySelectorAll('.answer-btn');
buttons.forEach(button => {
    button.addEventListener('click', function () {
        // this.dataset.question -> string containing the question number
        // this.dataset.answer   -> string containing the answer index
        // convert them to numbers so we can compare later
        const q = parseInt(this.dataset.question, 10);
        const a = parseInt(this.dataset.answer, 10);

        // remove the "selected" class from any other button belonging
        // to the same question so only one answer looks selected
        buttons.forEach(b => {
            if (parseInt(b.dataset.question, 10) === q) {
                b.classList.remove('selected');
            }
        });

        // mark this button as selected and remember the answer
        this.classList.add('selected');
        userAnswers[q] = a;
    });
});

// 4. This function will be called when the user clicks the "Ergebnisse anzeigen" button.
//    It compares the stored answers with the correct ones, counts points,
//    updates the HTML to show the score, and makes the result box visible.
function showResults() {
    let points = 0;

    // loop over each question in our correctAnswers object
    for (let q in correctAnswers) {
        if (userAnswers[q] === correctAnswers[q]) {
            points += 1; // correct answer, give a point
        }
    }

    // show the numerical score on the page
    const scoreEl = document.getElementById('score');
    scoreEl.textContent = points + ' / 5';

    // change the message depending on how well they did
    const msgEl = document.getElementById('resultMessage');
    if (points === 5) {
        msgEl.textContent = 'Perfekt, Sie sind ein Nichtigkeitsmeister!';
    } else {
        msgEl.textContent = 'Sie haben ' + points + ' Punkte erzielt. Versuchen Sie es noch einmal!';
    }

    // finally, display the result box (it was hidden with CSS initially)
    const resultBox = document.getElementById('result');
    resultBox.style.display = 'block';
}

// the HTML button already calls showResults() using onclick, so no event
// listener is needed here.  You could also attach it programmatically
// if you prefer.
