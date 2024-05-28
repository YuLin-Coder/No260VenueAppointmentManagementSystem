Validator = {mode:"", Require:/.+/, Email:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, Phone:/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/, Msisdn:/^(150|151|152|157|158|159|187|188|13[4-9]{1})[0-9]{8}$/, Url:/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/, IdCard:"this.IsIdCard(value)", Currency:/^\d+(\.\d+)?$/, Number:/^\d+$/, Zip:/^[1-9]\d{5}$/, QQ:/^[1-9]\d{4,8}$/, Integer:/^[-\+]?\d+$/,
 Integer2:/^\d+$/,IP:/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/, Double:/^[-\+]?\d+(\.\d+)?$/, English:/^[A-Za-z]+$/, Chinese:/^[\u0391-\uFFE5]+$/, Username:/^[a-z]\w{3,}$/i, UnSafe:/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/, IsSafe:function (str) {
	return !this.UnSafe.test(str);
}, SafeString:"this.IsSafe(value)", Filter:"this.DoFilter(value, getAttribute('accept'))", Limit:"this.limit(value.length,getAttribute('min'),  getAttribute('max'))", LimitB:"this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))", Date:"this.IsDate(value, getAttribute('min'), getAttribute('format'))", Repeat:"value == document.getElementsByName(getAttribute('to'))[0].value", Range:"getAttribute('min') <= (value|0) && (value|0) <= getAttribute('max')", Compare:"this.compare(value,getAttribute('operator'),getAttribute('to'))", Custom:"this.Exec(value, getAttribute('regexp'))", Group:"this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))", ErrorItem:[document.forms[0]], ErrorMessage:[], Validate:function (theForm, mode) {
	mode = mode || 1;
	this.mode = mode;
	try {
		theForm = document.getElementById(theForm);
	}
	catch (e) {
	}
	var obj = theForm || event.srcElement;
	var count = obj.all.length;
	this.ErrorMessage.length = 1;
	this.ErrorItem.length = 1;
	this.ErrorItem[0] = obj;
	for (var i = 0; i < count; i++) {
		var foundError = false;
		with (obj.all[i]) {
			var _dataType = getAttribute("dataType");
			
			//alert(_dataType);
			if (typeof (_dataType) == "object" || typeof (this[_dataType]) == "undefined") {
				continue;
			}
			this.ClearState(obj.all[i]);
			if (obj.all[i].disabled) {
				continue;
			}
			if (getAttribute("require") == "false" && value == "") {
				continue;
			}
			switch (_dataType) {
			  case "IdCard":
			  case "Date":
			  case "Repeat":
			  case "Range":
			  case "Compare":
			  case "Custom":
			  case "Group":
			  case "Limit":
			  case "LimitB":
			  case "SafeString":
			  case "Filter":
				if (!eval(this[_dataType])) {
					this.AddError(i, getAttribute("msg"));
					if (mode == 1) {
						foundError = true;
					}
				}
				break;
			  default:
				if (!this[_dataType].test(value)) {
				 
					this.AddError(i, getAttribute("msg"));
					if (mode == 1) {
						foundError = true;
					}
				}
				break;
			}
		}
		if (foundError == true) {
			break;
		}
	}
	if (this.ErrorMessage.length > 1) {
		var errCount = this.ErrorItem.length;
		switch (mode) {
		  case 1:
			alert(this.ErrorMessage.join("\n"));
			try {
				this.ErrorItem[1].focus();
			}catch(e){};
			break;
		  case 2:
			for (var i = 1; i < errCount; i++) {
				try {
					var span = document.createElement("SPAN");
					span.id = "__ErrorMessagePanel";
					span.style.color = "red";
					this.ErrorItem[i].parentNode.appendChild(span);
					span.innerHTML = this.ErrorMessage[i].replace(/\d+:/, "*");
				}
				catch (e) {
					alert(e.description);
				}
			}
			try {
				this.ErrorItem[1].focus();
			}catch(e){};
			break;
		  default:
			this.ErrorMessage = ["\u4ee5\u4e0b\u539f\u56e0\u5bfc\u81f4\u63d0\u4ea4\u5931\u8d25\uff1a\t\t\t\t"];
			alert(this.ErrorMessage.join("\n"));
			break;
		}
		return false;
	}
	return true;
}, limit:function (len, min, max) {
	min = min || 0;
	max = max || Number.MAX_VALUE;
	return min <= len && len <= max;
}, LenB:function (str) {
	return str.replace(/[^\x00-\xff]/g, "**").length;
}, ClearState:function (elem) {
	with (elem) {
		if (style.color == "red") {
			style.color = "";
		}
		var lastNode = parentNode.childNodes[parentNode.childNodes.length - 1];
		if (lastNode.id == "__ErrorMessagePanel") {
			parentNode.removeChild(lastNode);
		}
	}
}, AddError:function (index, str) {
	this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].all[index];
	if (this.mode == 1) {
		this.ErrorMessage[this.ErrorMessage.length] = str;
	} else {
		this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;
	}
}, Exec:function (op, reg) {
	return new RegExp(reg, "g").test(op);
}, compare:function (op1, operator, op2) {
	switch (operator) {
	  case "NotEqual":
		return (op1 != op2);
	  case "GreaterThan":
		return (op1 > op2);
	  case "GreaterThanEqual":
		return (op1 >= op2);
	  case "LessThan":
		return (op1 < op2);
	  case "LessThanEqual":
		return (op1 <= op2);
	  default:
		return (op1 == op2);
	}
}, MustChecked:function (name, min, max) {
  //alert('11');
	var groups = document.getElementsByName(name);
	var hasChecked = 0;
	min = min || 1;
	max = max || groups.length;
	for (var i = groups.length - 1; i >= 0; i--) {
		if (groups[i].checked) {
			hasChecked++;
		}
	}
	return min <= hasChecked && hasChecked <= max;
}, DoFilter:function (input, filter) {
	return new RegExp("^.+.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
}, IsIdCard:function (number) {
	var date, Ai;
	var verify = "10x98765432";
	var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
	var area = ["", "", "", "", "", "", "", "", "", "", "", "\u5317\u4eac", "\u5929\u6d25", "\u6cb3\u5317", "\u5c71\u897f", "\u5185\u8499\u53e4", "", "", "", "", "", "\u8fbd\u5b81", "\u5409\u6797", "\u9ed1\u9f99\u6c5f", "", "", "", "", "", "", "", "\u4e0a\u6d77", "\u6c5f\u82cf", "\u6d59\u6c5f", "\u5b89\u5fae", "\u798f\u5efa", "\u6c5f\u897f", "\u5c71\u4e1c", "", "", "", "\u6cb3\u5357", "\u6e56\u5317", "\u6e56\u5357", "\u5e7f\u4e1c", "\u5e7f\u897f", "\u6d77\u5357", "", "", "", "\u91cd\u5e86", "\u56db\u5ddd", "\u8d35\u5dde", "\u4e91\u5357", "\u897f\u85cf", "", "", "", "", "", "", "\u9655\u897f", "\u7518\u8083", "\u9752\u6d77", "\u5b81\u590f", "\u65b0\u7586", "", "", "", "", "", "\u53f0\u6e7e", "", "", "", "", "", "", "", "", "", "\u9999\u6e2f", "\u6fb3\u95e8", "", "", "", "", "", "", "", "", "\u56fd\u5916"];
	var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
	if (re == null) {
		return false;
	}
	if (re[1] >= area.length || area[re[1]] == "") {
		return false;
	}
	if (re[2].length == 12) {
		Ai = number.substr(0, 17);
		date = [re[9], re[10], re[11]].join("-");
	} else {
		Ai = number.substr(0, 6) + "19" + number.substr(6);
		date = ["19" + re[4], re[5], re[6]].join("-");
	}
	if (!this.IsDate(date, "ymd")) {
		return false;
	}
	var sum = 0;
	for (var i = 0; i <= 16; i++) {
		sum += Ai.charAt(i) * Wi[i];
	}
	Ai += verify.charAt(sum % 11);
	return (number.length == 15 || number.length == 18 && number == Ai);
}, IsDate:function (op, formatString) {
	formatString = formatString || "ymd";
	var m, year, month, day;
	switch (formatString) {
	  case "yyyy-mm-dd":
		m = op.match(new RegExp("^((\\d{4}))([-])(\\d{2})([-])(\\d{2})$"));
		if (m == null) {
			return false;
		}
		day = m[6];
		month = m[4] * 1;
		year = (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
		break;
	  case "ymd":
		m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
		if (m == null) {
			return false;
		}
		day = m[6];
		month = m[5] * 1;
		year = (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
		break;
	  case "dmy":
		m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
		if (m == null) {
			return false;
		}
		day = m[1];
		month = m[3] * 1;
		year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
		break;
	  default:
		break;
	}
	if (!parseInt(month)) {
	
		return false;
	}
	month = month == 0 ? 12 : month;
	var date = new Date(year, month - 1, day);
	return (typeof (date) == "object" && year == date.getFullYear() && month == (date.getMonth() + 1) && day == date.getDate());
	function GetFullYear(y) {
		return ((y < 30 ? "20" : "19") + y) | 0;
	}
}, isEmpty:function (value) {
	return this.Require.test(value);
}, isMsisdn:function (value) {
	return this.Msisdn.test(value);
	},
   isInteger2:function (value) {
	return this.Integer2.test(value);
	},
	isIP:function (value) {
	if(this.IP.test(value))
	{
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256)
		 return true;
	}
	return false;
	
	}
	
};
MyWindow = {OpenCenterWindow:function (url, name, height, width) {
	var str = " height=" + height + ",innerHeight=" + height;
	str += ",width=" + width + ",innerWidth=" + width;
	if (window.screen) {
		var ah = screen.availHeight - 30;
		var aw = screen.availWidth - 10;
		var xc = (aw - width) / 2;
		var yc = (ah - height) / 2;
		xc = xc >= 0 ? xc : 0 ;
		yc = yc >= 0 ? yc : 0 ;
		str += ",left=" + xc + ",screenX=" + xc;
		str += ",top=" + yc + ",screenY=" + yc;
	}
	win=window.open(url, name, str);
	if(url!=''){
	   win.document.write("<body><center style='padding-top:"+height/4+"px;'><font style=\"font-size:13px\">"+"<img src=\"/czndm/images/wait1.gif\">&nbsp;数据加载中，请稍后<b>...</b/>"+"</font></center></body>");
	   win.location=url;
	}
	return win;
}, OpenCenterWindowScroll:function (url, name, height, width) {
	var str = " height=" + height + ",innerHeight=" + height;
	str += ",width=" + width + ",innerWidth=" + width;
	if (window.screen) {
		var ah = screen.availHeight - 30;
		var aw = screen.availWidth - 10;
		var xc = (aw - width) / 2;
		var yc = (ah - height) / 2;
		xc = xc >= 0 ? xc : 0 ;
		yc = yc >= 0 ? yc : 0 ;
		str += ",left=" + xc + ",screenX=" + xc;
		str += ",top=" + yc + ",screenY=" + yc;
		str += ",scrollbars=yes";
	}
	return window.open(url, name, str);
}
};
function changeColor() {
	var table = document.getElementById("row");
	var rows = table.getElementsByTagName("tr");
	for (i = 0; i < rows.length; i++) {
		var oldClass;
		rows[i].onmouseover = function () {
			oldClass = this.className;
			if (this.className != "clickRow") {
				this.className = "over";
			}
		};
		rows[i].onmouseout = function () {
			if (this.className != "clickRow") {
				this.className = oldClass;
			}
		};
	}
}
function windowAllScreen() {
	self.moveTo(0, 0);
	self.resizeTo(screen.availWidth, screen.availHeight);
}

function changeColor2(src) {
	var table = document.getElementById(src);
	var rows = table.getElementsByTagName("tr");
	for (var i = 0; i < rows.length; i++) {
		var oldClass;
		rows[i].onmouseover = function () {
			oldClass = this.className;
			if (this.className != "clickRow") {
				this.className = "over";
			}
		};
		rows[i].onmouseout = function () {
			if (this.className != "clickRow") {
				this.className = oldClass;
			}
		};
	}
}
function clickRow(src) {
	var row = src;
	while (row != null && row.tagName != "TR") {
		row = row.parentElement;
	}
	var table = document.getElementById("row");
	var rows = table.getElementsByTagName("tr");
	rows[row.rowIndex].className = "clickRow";
}

	function enableButton1(src,bln) {			
		
		if(bln)
		{		
			src.disabled=false;
			//src.className="btn";
			src.style.padding="2px 2px 0px 2px";
			src.style.background="transparent";
			src.style.color="#ffffff";
			 ////src.style.border="#7FB4DE 1px silver";
			src.style.filter="progid:DXImageTransform.Microsoft.Gradient(enabled='true',startColorStr='#3897D1', endColorStr='#cEf4F9')";
			src.style.cursor = "hand";
		
		}
		else
		{
		  	src.disabled=true;
			src.style.border="1px solid #9eb6ce";
			src.style.filter="progid:DXImageTransform.Microsoft.Gradient(enabled='true',startColorStr='#fafcfd', endColorStr='#d2ddeb')";	
			src.style.color="#000000";
		}
		
	}

function disableImage(src) {
	src.onclick = function () {
	};
	src.style.filter = "gray";
	src.style.cursor = "";
}
	
		//
function enableImage(src, fun1) {
	src.onclick = function () {
		fun1(src);
	};
	src.style.filter = "black";
	src.style.cursor = "hand";
}
/*	图片按钮结合防止重复提交的示例代码
	
	
		<img src="<%=Constants.SAVE_BUTTON %>" onclick="save(this);" 

name="saveButton" align="absmiddle" style="cursor:hand" />
		enableImage(form1.saveButton,save);
		
		function save(src){
	disableImage(src);
      	form1.submit();

	}

*/
function showElement(src) {
	document.getElementById(src).style.display = "";
}
function hideElement(src) {
	document.getElementById(src).style.display = "none";
}
function disableButton(src) {
	src.disabled = true;
}
function enableButton(src) {
	src.disabled = false;
}
function getRow(src) {
	var row = src;
	while (row && row.tagName != "TR") {
		row = row.parentElement;
	}
	return row;
}

  //某个名称的checkbox被选中的个数
function getCheckBoxCheckedCount(form1, checkBoxName) {
	var checks = document[form1].all[checkBoxName];
	var count = 0;
	for (var i = 0; i < checks.length; i++) {
		var chk = checks[i];
		if (chk.checked == true) {
			count++;
		}
	}
	return count;
}

function getCheckBoxValues(form1, checkBoxName,delim)
{
	var result="";
	var checks = document[form1].all[checkBoxName];
	if(checks==null)   //no one
	{	
		result="";
	}
	else if(checks.length==undefined)  //only one
	{
		if(checks.checked==true)
		   result=checks.value;
	}
	else    //more than one
	{
		for (var i = 0; i < checks.length; i++) {
			var chk = checks[i];
			if (chk.checked == true) {
				result=result+chk.value+delim;
				
			}
		}
		if(result!='')
		{
			result=result.substring(0,result.length-1);
		}
	}
	return result;
}

function setRowElementReadOnly(tableId, rowIndex) {
	var tb = document.getElementById(tableId);
	var row = tb.rows[rowIndex];
	var elements = row.all;
	var i = 0;
	for (i = 0; i < elements.length; i++) {
		var ele = elements[i];
		if (ele.tagName == "INPUT") {
			ele.readOnly = true;
		} else {
			if (ele.tagName == "TEXTAREA") {
				ele.readOnly = true;
			} else {
				if (ele.tagName == "SELECT") {
					ele.onclick = function () {
						this.blur();
					};
					ele.onmouseover = function () {
						this.setCapture();
					};
					ele.onmouseout = function () {
						this.releaseCapture();
					};
				}
			}
		}
	}
}
function getRowElement(tableId,rowIndex, eleName) {
	var tb = document.getElementById(tableId);
	var row = tb.rows[rowIndex];
	
	var elements = row.all;
	for (j = 0; j < elements.length; j++) {
		var ele = elements[j];
		if (ele && ele.name == eleName) {
			return ele;
		}
	}
	return null;
}

function getRowElement2(tableId,eleName,src)
{
	var row=getRow(src);
	return getRowElement(tableId,row.rowIndex,eleName);	
}
function delRow(tableId) {
	var tb = document.getElementById(tableId);
	var src = event.srcElement;
	var row = getRow(src);
	tb.deleteRow(row.rowIndex);
}
function resetElement(id) {
	var container = document.getElementById(id);
	var ipts = container.getElementsByTagName("INPUT");
	for (var i = 0; i < ipts.length; i++) {
		if (ipts[i].type == "text" || ipts[i].type == "hidden" || ipts[i].type == "password") {
			ipts[i].value = "";
		}
		if (ipts[i].type == "checkbox" || ipts[i].type == "radio") {
			ipts[i].checked = false;
		}
		if (ipts[i].type == "file") {
			ipts[i].outerHTML = ipts[i].outerHTML;
		}
	}
	var tas = container.getElementsByTagName("TEXTAREA");
	for (var i = 0; i < tas.length; i++) {
		tas[i].value = "";
	}
	var sts = container.getElementsByTagName("SELECT");
	for (var i = 0; i < sts.length; i++) {
		sts[i].selectedIndex = 0;
	}
}
function disableElement(id, disabled) {
	var container = document.getElementById(id);
	var ipts = container.getElementsByTagName("INPUT");
	for (var i = 0; i < ipts.length; i++) {
		ipts[i].disabled = disabled;
	}
	var tas = container.getElementsByTagName("TEXTAREA");
	for (var i = 0; i < tas.length; i++) {
		tas[i].disabled = disabled;
	}
	var sts = container.getElementsByTagName("SELECT");
	for (var i = 0; i < sts.length; i++) {
		sts[i].disabled = disabled;
	}
}
function getRadioValue(form1,radioName)
{

   var radios=form1[radioName];
   var i=0;
   for(i=0;i<radios.length;i++)
   {
   	  radio=radios[i];
   	  if(radio.checked==true)
   	  {
   	     return radio.value;
   	  }
   }
}
function checknum(src,msg)
{
	if(src.value=="")
		return false;
    if(!Validator.Number.test(src.value))
    {
    	if(msg!=null && msg!=undefined)
    	   alert(msg);
    	
        src.focus();
        return false ;
    }
    return true;
}

/**
* yearMonth-format : yyyymm
*/
function checkYearMonth(src)
{
    if(src.value==""||src.value.length!=6)
     return false;
	if(!checknum(src))
	  return false;
	var yearMonth=src.value;
	var year=yearMonth.substring(0,4);
	var month=yearMonth.substring(4,6);
	var day=15;
	try
	{
		
		
		if(mon>12||mon<1)
		  return false;
	}
	catch(e){return false;}
	  
}
function getTable(src)
{
	var tb = src;
	while (tb && tb.tagName != "TABLE") {
		tb = tb.parentElement;
	}
	return tb;
}
function delRow2(src)
{
	var tb=getTable(src);
	delRow(tb.id);
}

function isArray(eleName)
{
	
	var eles=document.getElementsByName(eleName);
	if(eles==null)
	  return false;
	if(eles.length>1)
	{
	 	return true;
	}
	return false;
}


function getTd(src) {
	var td = src;
	while (td && td.tagName != "TD") {
		td = td.parentElement;
	}
	return td;
}

/*
function rebuildRowNum (tableId) {
	var tb = document.getElementById(tableId);
	var rows = tb.rows;
	var cell;
	var rowspan;
	var rownum=1;
	for (i = 1; i < rows.length;  ) {
		cell = rows[i].cells[0];
		rowspan=cell.rowSpan;
		cell.innerText = rownum;
		i=rowspan+i;
		rownum=rownum+1;
	}
}
*/
function rebuildRowNum (tableId) {
	var tb = document.getElementById(tableId);
	var rows = tb.rows;
	var cell=rows[0].cells[0];
	var rowspan=cell.rowSpan;
	var rownum=1;
	for (i = rowspan; i < rows.length;  ) {
		cell = rows[i].cells[0];
		rowspan=cell.rowSpan;
		cell.innerText = rownum;
		i=rowspan+i;
		rownum=rownum+1;
	}
}

function selectAll(name) {   
    var ids = document.getElementsByName(name);   
    for (var i = 0; i < ids.length; i++)    
    {          
        if(ids[i].disabled==false)
        {
        	ids[i].checked = true;   
        }  
    }   
}   
function  unSelectAll(name) {   
    var ids = document.getElementsByName(name);   
    for (var i = 0; i < ids.length; i++)    
    {          
          ids[i].checked = false;   
    }   
}   

function isPow2(num)
{
	var i=0;
	for(;i<=10;i++ )
	{
		if(Math.pow(2,i)==num)
		{
			return true;
		}	
	}
	return false;
}

function getAllElementValues(form1, eleName,delim)
{
	var result="";
	var eles = form1.all[eleName];
	
	if(eles==null)   //no one
	{	
		result="";
	}
	else if(eles.length==undefined)  //only one
	{
	  
	   if(eles.type=='checkbox')
	   {
	   	  if(eles.checked)
	   	    result=eles.value;
	   }
	   else
	     result=eles.value;
	}
	else    //more than one
	{
		for (var i = 0; i < eles.length; i++) {
			var ele = eles[i];
			
			if(ele.type=='checkbox')
			{
			    if(ele.checked)
				    result=result+ele.value+delim;
			}
			else
			  result=result+ele.value+delim;
		}
		if(result!='')
		{
			result=result.substring(0,result.length-1);
		}
	}
	return result;
}

function deleteAllRow(tab)
{
	for(var i=tab.rows.length;i>1;i--)
	{
		tab.deleteRow(tab.rows.length-1);
	}
}

String.prototype.endWith = function(oString){   
	var reg = new RegExp(oString+"$");   
	return reg.test(this);   
}
String.prototype.startWith = function(oString){   
	var reg = new RegExp("^"+oString);   
	return reg.test(this);   
}
String.prototype.trim = function(){return this.replace(/(^\s*)|(\s*$)/g,"");}


$(function(){
	$('.topMenu .arrow').click(function(){
		if($('#menu').hasClass('cur')){
			$('#menu').removeClass('cur');
			$('#menu').slideUp(300);
		}else{
			$('#menu').addClass('cur');
			$('#menu').slideDown(300);
		}
		$('.arrow .up').toggle();
		$('.arrow .down').toggle();
	});
});
