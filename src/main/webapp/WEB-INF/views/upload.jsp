<div id="mulitplefileuploader">Upload</div>
<div id="status"></div>
<div id="filePreview"></div>
<script>
$(document).ready(function()
{
var settings = {
    url: "/springsecurity/merchant/uploadFile",
    dragDrop:true,
    fileName: "file",
    allowedTypes:"jpg,png,gif",	
    returnType:"html",
	 onSuccess:function(files,data,xhr)
    {
       if(data=='1'){
    	   $('filePreview').html('<span class="error">File upload failed</span>');
       }else if(data=='2'){
    	   $('filePreview').html('<span class="error">File is empty</span>');
       }else{
    	   $('filePreview').html('<img src="'+data+'" class="imgPreview"/>');
       }
    },
    showDelete:false,
    deleteCallback: function(data,pd)
	{
    for(var i=0;i<data.length;i++)
    {
        $.post("delete.php",{op:"delete",name:data[i]},
        function(resp, textStatus, jqXHR)
        {
            //Show Message  
            $("#status").append("<div>File Deleted</div>");      
        });
     }      
    pd.statusbar.hide(); //You choice to hide/not.

}
}
var uploadObj = $("#mulitplefileuploader").uploadFile(settings);
});
</script>