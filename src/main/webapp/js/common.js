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
'use strict';
function r(f){/in/.test(document.readyState)?setTimeout('r('+f+')',9):f()}
r(function(){
    if (!document.getElementsByClassName) {
        // Поддержка IE8
        var getElementsByClassName = function(node, classname) {
            var a = [];
            var re = new RegExp('(^| )'+classname+'( |$)');
            var els = node.getElementsByTagName("*");
            for(var i=0,j=els.length; i < j; i++)
                if(re.test(els[i].className))a.push(els[i]);
            return a;
        }
        var videos = getElementsByClassName(document.body,"youtube");
    } else {
        var videos = document.getElementsByClassName("youtube");
    }
    var nb_videos = videos.length;
    for (var i=0; i < nb_videos; i++) {
        // Находим постер для видео, зная ID нашего видео
        videos[i].style.backgroundImage = 'url(http://i.ytimg.com/vi/' + videos[i].id + '/sddefault.jpg)';
        // Размещаем над постером кнопку Play, чтобы создать эффект плеера
        var play = document.createElement("div");
        play.setAttribute("class","play");
        videos[i].appendChild(play);
        videos[i].onclick = function() {
            // Создаем iFrame и сразу начинаем проигрывать видео, т.е. атрибут autoplay у видео в значении 1
            var iframe = document.createElement("iframe");
            var iframe_url = this.id + "?autoplay=1&autohide=1";
            if (this.getAttribute("data-params")) iframe_url+='&'+this.getAttribute("data-params");
            iframe.setAttribute("src",iframe_url);
            iframe.setAttribute("frameborder",'0');
            // Высота и ширина iFrame будет как у элемента-родителя
            iframe.style.width  = this.style.width;
            iframe.style.height = this.style.height;
            // Заменяем начальное изображение (постер) на iFrame
            this.parentNode.replaceChild(iframe, this);
        }
    }
});







