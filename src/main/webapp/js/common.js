function trim(sInString) {
    sInString = sInString.replace(/ /g, '');
    return sInString.replace(/(^\s+)|(\s+$)/g, "");
}
function findPos(obj) {
    var curtop = 0;
    if (obj.offsetParent) {
        do {
            curtop += obj.offsetTop;
        } while (obj = obj.offsetParent);
        return [curtop];
    }
}
function saveQuestion(contextPath) {
    if (isQAEmpty()) {
       return;
    }
    var form = document.getElementById("addQuestionForm");
    form.method = "POST";
    form.submit();
}
function editQuestion(contextPath) {
    if (isQAEmpty()) {
        return;
    }
    var form = document.getElementById("editQuestionForm");
    form.method = "POST";
    form.submit();
}
function isQAEmpty(){
    var question = tinyMCE.get("QUESTION_TEXT_PARAM").getContent();
    var answer = tinyMCE.get("ANSWER_TEXT_PARAM1").getContent();
    if (trim(question).length == 0) {
           alert("Question cannot be empty.");
           return true;
    }
    if (trim(answer).length == 0) {
           alert("Answer cannot be empty.");
           return true;
    }
    return false;
}
function changeTest(contextPath) {
    var index = document.getElementById("TEST_ID_PARAM").selectedIndex;
    var value = document.getElementById("TEST_ID_PARAM").options[index].value;
    var form = document.getElementById("addQuestionForm");
    form.action = contextPath + "/servlet/AddQuestionEntry?SELECTED_TEST_ID=" + value;
    form.method = "POST";
    form.submit();
}

function addNextAnswer(buttonName) {
    var numberElement = document.getElementById('answerNumber');
    var number = Number(numberElement.value) + 1;
    var answersDiv= document.getElementById('answersDiv');

    var answerBlock = document.createElement('div');
    answerBlock.setAttribute("id", "answerblock" + number);

    answersDiv.appendChild(answerBlock);

    var br = document.createElement('br');
    var br1 = document.createElement('br');

    answerBlock.appendChild(br);
    answerBlock.appendChild(br1);

    addCheckBox(answerBlock, number);
    addTextArea(answerBlock, number);
    addDeleteButton(answerBlock, number, buttonName);

    addWYSIWG();
    numberElement.value = number;
}

function addCheckBox(answersDiv, number){
    var checkbox = document.createElement('input');
    checkbox.setAttribute("type","checkbox");
    checkbox.setAttribute("name","checkbox" + number);
    answersDiv.appendChild(checkbox);
}

function addTextArea(answersDiv,number){
    var divElement = document.createElement('div');
    divElement.setAttribute("class","answerDiv");
    divElement.setAttribute("id","answer" + number);

    var textArea = document.createElement('textarea');
    textArea.setAttribute("name","ANSWER_TEXT_PARAM" + number);
    divElement.appendChild(textArea);

    answersDiv.appendChild(divElement);
}

function addDeleteButton(answersDiv, number, addDeleteButton){
    var buttonElement = document.createElement('input');
    buttonElement.setAttribute("type", "button");
    buttonElement.setAttribute("value", addDeleteButton);
    buttonElement.setAttribute("onclick", "deleteAnswer("+number+")");
    buttonElement.setAttribute("id", "deleteAnswer" + number);

    answersDiv.appendChild(buttonElement);
}

function deleteAnswer(number){
    var element = document.getElementById('answerblock' + number);
    element.parentNode.removeChild(element);
}







