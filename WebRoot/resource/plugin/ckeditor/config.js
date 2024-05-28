/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
	
	//图片处理
	config.pasteFromWordRemoveStyles = true;
	//config.filebrowserImageUploadUrl = ctx_fck+"/file/fckupload.html"; 
	config.filebrowserImageUploadUrl = ctx_fck+"/file/fckupload.html";
	
	
};
