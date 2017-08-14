//添加新调研品种
function addNewBreedSubmit(){
    var pdClassesCode = localStorage.pdClassesCode;
    var pdMachiningCode = localStorage.pdMachiningCode;
    var buyerId = localStorage.buyerId;
    var saleName = $("#saleName").val();
    var scientificName = $("#scientificName").val();
    var popularName = $("#popularName").val();

    var flickerAPI = url+'/by/add/newCategory';
    var paramData = {"client": "abcd","auth": "xxxx","loginid": "a124","param": {"buyerId":buyerId,"classesCode":pdClassesCode,"machiningCode":pdMachiningCode,"saleName":saleName,"scientificName":scientificName,"popularName":popularName,"updId":localStorage.accessAccountName}};
    $.ajax({
        type : "POST",
        async:false,
        url:flickerAPI,
        timeout:60,
        dataType:'json',
        contentType:"application/json",
        data:JSON.stringify(paramData),
        success:function(data){
            if(data.status == "S"){
                window.location = "BY121214.html";
            }else{
                $("#errorMessageDiv").css("display","block");
                $("#errorMessageDiv").html("<span style='padding-left:20px;'>"+data.message+"</span>");
            }
        },
        error:function(){
            alert("error");
        }
    });
}
function returnPage(){
    window.location = "BY121214.html";
}