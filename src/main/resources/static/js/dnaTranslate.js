//    Sequence Manipulation Suite. A collection of simple JavaScript programs
//    for generating, formatting, and analyzing short DNA and protein
//    sequences.
//    Copyright (C) 2020 Paul Stothard stothard@ualberta.ca
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.
//

//Written by Paul Stothard, University of Alberta, Canada

function translateDna(theDocument) {
    translate(theDocument);
    return true;
}

// Check if the form is empty
function checkFormElement(formElement) {
    if (formElement.value.search(/\S/) == -1) {
      alert("Please enter some text.");
      return false;
    }
    return true;
}

// Checking the length of sequence
function checkSequenceLength(text, maxInput) {
    if (getSequenceFromFasta(text).replace(/[^A-Za-z]/g, "").length > maxInput) {
      alert(
        "Please enter a sequence consisting of less than or equal to " +
          maxInput +
          " characters."
      );
      return false;
    } else {
      return true;
    }
}

function getSequenceFromFasta(sequenceRecord) {
    if (sequenceRecord.search(/\>[^\f\n\r]+[\f\n\r]/) != -1) {
      sequenceRecord = sequenceRecord.replace(/\>[^\f\n\r]+[\f\n\r]/, "");
    }
    return sequenceRecord;
}


function translate(theDocument) {
    var newDna = "";
    var title = "";
    var maxInput = 200000000;

    var geneticCode = "/gc[acgturyswkmbdhvn]/=A," +
        "/[tu]g[ctuy]/=C," +
        "/ga[tcuy]/=D," +
        "/ga[agr]/=E," +
        "/[tu][tu][tcuy]/=F," +
        "/gg[acgturyswkmbdhvn]/=G," +
        "/ca[tcuy]/=H," +
        "/a[tu][atcuwmhy]/=I," +
        "/aa[agr]/=K," +
        "/c[tu][acgturyswkmbdhvn]|[tu][tu][agr]|[ctuy][tu][agr]/=L," +
        "/a[tu]g/=M," +
        "/aa[tucy]/=N," +
        "/cc[acgturyswkmbdhvn]/=P," +
        "/ca[agr]/=Q," +
        "/cg[acgturyswkmbdhvn]|ag[agr]|[cam]g[agr]/=R," +
        "/[tu]c[acgturyswkmbdhvn]|ag[ct]/=S," +
        "/ac[acgturyswkmbdhvn]/=T," +
        "/g[tu][acgturyswkmbdhvn]/=V," +
        "/[tu]gg/=W," +
        "/[tu]a[ctuy]/=Y," +
        "/[tu]a[agr]|[tu]ga|[tu][agr]a/=*";

    // Replace the value here with the value from the form
    if (
        checkFormElement(theDocument.forms[0].elements[0]) == false ||
        checkSequenceLength(theDocument.forms[0].elements[0].value, maxInput) ==
        false
    ) {
        return false;
    }

    geneticCode = geneticCode.split(/,/);

    if (checkGeneticCode(geneticCode) == false) {
        return false;
    }

    var rfText =
        theDocument.forms[0].elements[4].options[
            theDocument.forms[0].elements[4].selectedIndex
        ].value;
    if (
        theDocument.forms[0].elements[4].options[
            theDocument.forms[0].elements[4].selectedIndex
        ].value.match(/^\d+$/)
    ) {
        rfText++;
    } else {
        rfText =
            '"' +
            theDocument.forms[0].elements[4].options[
                theDocument.forms[0].elements[4].selectedIndex
            ].value +
            '"';
    }

    openWindow("Translate");
    openPre();
    var arrayOfFasta = getArrayOfFasta(theDocument.forms[0].elements[0].value);
    for (var i = 0; i < arrayOfFasta.length; i++) {
        newDna = getSequenceFromFasta(arrayOfFasta[i]);
        title = getTitleFromFasta(arrayOfFasta[i]);
        newDna = removeNonDna(newDna);
        outputWindow.document.write("&gt;rf " + rfText + " " + title + "\n");
        writeTranslation(
            newDna,
            geneticCode,
            theDocument.forms[0].elements[4].options[
                theDocument.forms[0].elements[4].selectedIndex
            ].value,
            theDocument.forms[0].elements[5].options[
                theDocument.forms[0].elements[5].selectedIndex
            ].value
        );
        outputWindow.document.write("\n\n");
    }
    closePre();
    closeWindow();
    return true;
}

function writeTranslation(dnaSequence, geneticCode, startPos, strand) {
    var geneticCodeMatchExp = getGeneticCodeMatchExp(geneticCode);
    var geneticCodeMatchResult = getGeneticCodeMatchResult(geneticCode);

    if (strand == "reverse") {
        dnaSequence = reverse(complement(dnaSequence));
    }
    if (startPos == "uppercase") {
        dnaSequence = dnaSequence.replace(/[a-z]/g, "");
    } else {
        dnaSequence = dnaSequence.substring(parseInt(startPos), dnaSequence.length);
    }

    //don't translate if fewer than three bases
    if (dnaSequence.replace(/[^A-Za-z]/g, "").length < 3) {
        return "";
    }

    dnaSequence = dnaSequence.replace(/(...)/g, function (str, p1, offset, s) {
        return " " + p1 + " ";
    });

    for (var i = 0; i < geneticCodeMatchExp.length; i++) {
        dnaSequence = dnaSequence.replace(
            geneticCodeMatchExp[i],
            geneticCodeMatchResult[i]
        );
    }

    dnaSequence = dnaSequence.replace(/\S{3}/g, "X");
    dnaSequence = dnaSequence.replace(/\s\S{1,2}$/, "");
    dnaSequence = dnaSequence.replace(/\s/g, "");
    outputWindow.document.write(addReturns(dnaSequence));
    return true;
}