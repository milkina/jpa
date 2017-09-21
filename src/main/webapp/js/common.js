
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

function saveCategory() {
    var form = document.getElementById("addCategoryForm");
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
    var answer = tinyMCE.get("ANSWER_TEXT_PARAM").getContent();
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


function addNextAnswer() {
    var answersDiv= document.getElementById('answersDiv');

    var br = document.createElement('br');
    var br1 = document.createElement('br');

    var strongElement = document.createElement('strong');
    var textNode = document.createTextNode("Answer:   ");
    strongElement.appendChild(textNode);

    var checkbox = document.createElement('input');
    checkbox.setAttribute("type","checkbox");

    var textArea = document.createElement('textarea');
    textArea.setAttribute("rows","7");
    textArea.setAttribute("cols","60");
    textArea.setAttribute("name","ANSWER_TEXT_PARAM");

    answersDiv.appendChild(br);
    answersDiv.appendChild(br1);
    answersDiv.appendChild(strongElement);
    answersDiv.appendChild(checkbox);
    answersDiv.appendChild(textArea);
}







