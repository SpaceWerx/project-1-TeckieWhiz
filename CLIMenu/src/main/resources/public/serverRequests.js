/**
 * 
 */
 
var newXHR = null;   

function sendAjaxRequest(type, url, data, successCallback,failedCallback,userid) 
{

  newXHR = new XMLHttpRequest() || new window.ActiveXObject("Microsoft.XMLHTTP");
  newXHR.open(type, url, true);
  if(data!=null)
  	newXHR.send(data);
  else
  	newXHR.send();
  
  newXHR.onreadystatechange = function() 
  {
	if(this.readyState === 4) 
    {
		if(successCallback !=null)
		{
			if(this.status === 200)
      		successCallback(this.response);
    	else
    		failedCallback(this.response);
		}else
		{
			return this.response;
		}
		
	}
  };
}
