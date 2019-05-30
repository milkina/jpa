function markAnswered(contextPath, id) {
    var changeElement = document.getElementById("isAnswered" + id);
    //if person isn't logged in return
    var isLoggedIn = document.getElementById("isLogin");
    if (!isLoggedIn) {
        changeElement.checked = false;
        alert("Please log in or register to have a possibility to mark questions.");
        return;
    }
   var liElementId = document.getElementById("li" + id);
    var url = contextPath + "/change-answered-question";
    var param = "";
    if (changeElement.checked) {
        param = param + "Checked=" + id;
        if(liElementId){
          liElementId.className = "answeredQuestion";
        }
    } else {
        param = param + "Unchecked=" + id;
         if(liElementId){
           liElementId.className = "notAnsweredQuestion";
         }
    }
    req.open("POST", url, true);
    req.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded");
    req.send(param);
}

function changeShowingMode(element) {
    //if person isn't logged in return
    var isLoggedIn = document.getElementById("isLogin");
    if (!isLoggedIn) {
        alert("Please log in or register to have a possibility to mark questions.");
        return;
    }
    var checkedValue = element.value;
    var answeredQuestions = getElementsByClassName(document.body, "answeredQuestion");
    var notAnsweredQuestion = getElementsByClassName(document.body, "notAnsweredQuestion");
    if (checkedValue == "ALL") {
        changeLiArrayStyle(answeredQuestions, "block");
        changeLiArrayStyle(notAnsweredQuestion, "block");
    } else if (checkedValue == "ANSWERED") {
        changeLiArrayStyle(answeredQuestions, "block");
        changeLiArrayStyle(notAnsweredQuestion, "none");
    } else if (checkedValue == "NOT_ANSWERED") {
        changeLiArrayStyle(answeredQuestions, "none");
        changeLiArrayStyle(notAnsweredQuestion, "block");
    }
}
function changeLiArrayStyle(array, newStyle) {
    for (var i = 0; i < array.length; i++) {
        array[i].style.display = newStyle;
    }
}
function getElementsByClassName(node, classname) {
    var a = [];
    var re = new RegExp('(^| )' + classname + '( |$)');
    var els = node.getElementsByTagName("*");
    for (var i = 0,j = els.length; i < j; i++)
        if (re.test(els[i].className))a.push(els[i]);
    return a;
}
 function showAnswer(i,readMessage,hideMessage) {
           var element = document.getElementById("a" + i);
           var answerElement = document.getElementById("answer" + i);
            if (answerElement.style.display == "block"){
               answerElement.style.display = "none";
               element.innerHTML = readMessage;
            }else{
                answerElement.style.display = "block";
                element.innerHTML = hideMessage;
            }
       }

function deleteQuestion(contextPath, questionId,categoryPath,testPath) {
    if (confirm("Are you sure you want to delete the question?")) {
        window.location.href  = contextPath + '/delete-question?'+
        'QUESTION_ENTRY_ID_PARAM='+questionId+'&CATEGORY_PATH='+categoryPath+
        '&TEST_PATH='+testPath+'&EDIT_MODE_PARAM=DELETE';
    }
}

function approveQuestion(contextPath, questionId) {
    if (confirm("Are you sure you want to approve the question?")) {
        window.location.href  = contextPath + '/approve-question?'+
        'QUESTION_ENTRY_ID_PARAM=' + questionId;
    }
}
 function newXMLHttpRequest() {
     var xmlreq = false;
     if (window.XMLHttpRequest) {
         xmlreq = new XMLHttpRequest();
     } else if (window.ActiveXObject) {
         try {
             xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
         } catch (e1) {
             try {
                 xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
             } catch (e2) {
             }
         }
     }
     return xmlreq;
 }
 var req = newXMLHttpRequest();
 function sendAJAX(name, number, contextPath) {
     var idField = document.getElementById("userid");
     var url = contextPath + "/servlet/CreateExam";

     req.open("POST", url, true);
     req.onreadystatechange = reply;
     req.setRequestHeader("Content-Type",
             "application/x-www-form-urlencoded");

     req.send("examName=" + name + "&examCount=" + number);
 }




