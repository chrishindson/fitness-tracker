!function(t,e){"function"==typeof define&&define.amd?define([],e):"object"==typeof exports?module.exports=e():t.MOJFrontend=e()}(this,function(){var t={removeAttributeValue:function(t,e,o){var i,n;t.getAttribute(e)&&(t.getAttribute(e)==o?t.removeAttribute(e):(i=new RegExp("(^|\\s)"+o+"(\\s|$)"),(n=t.getAttribute(e).match(i))&&3==n.length&&t.setAttribute(e,t.getAttribute(e).replace(i,n[1]&&n[2]?" ":""))))},addAttributeValue:function(t,e,o){t.getAttribute(e)?new RegExp("(^|\\s)"+o+"(\\s|$)").test(t.getAttribute(e))||t.setAttribute(e,t.getAttribute(e)+" "+o):t.setAttribute(e,o)},dragAndDropSupported:function(){return void 0!==document.createElement("div").ondrop},formDataSupported:function(){return"function"==typeof FormData},fileApiSupported:function(){var t=document.createElement("input");return t.type="file",void 0!==t.files},nodeListForEach:function(t,e){if(window.NodeList.prototype.forEach)return t.forEach(e);for(var o=0;o<t.length;o++)e.call(window,t[o],o,t)},initAll:function(e){var o=void 0!==(e=void 0!==e?e:{}).scope?e.scope:document,i=o.querySelectorAll('[data-module="moj-add-another"]');t.nodeListForEach(i,function(e){new t.AddAnother(e)});var n=o.querySelectorAll('[data-module="moj-multi-select"]');t.nodeListForEach(n,function(e){new t.MultiSelect({container:e.querySelector(e.getAttribute("data-multi-select-checkbox")),checkboxes:e.querySelectorAll("tbody .govuk-checkboxes__input")})});var a=o.querySelectorAll('[data-module="moj-password-reveal"]');t.nodeListForEach(a,function(e){new t.PasswordReveal(e)});var s=o.querySelectorAll('[data-module="moj-rich-text-editor"]');t.nodeListForEach(s,function(e){var o={textarea:$(e)},i=e.getAttribute("data-moj-rich-text-editor-toolbar");if(i){var n=i.split(",");for(var a in o.toolbar={},n)o.toolbar[n[a]]=!0}new t.RichTextEditor(o)});var r=o.querySelectorAll('[data-module="moj-search-toggle"]');t.nodeListForEach(r,function(e){new t.SearchToggle({toggleButton:{container:$(e.querySelector(".moj-search-toggle__toggle")),text:e.getAttribute("data-moj-search-toggle-text")},search:{container:$(e.querySelector(".moj-search"))}})});var l=o.querySelectorAll('[data-module="moj-sortable-table"]');t.nodeListForEach(l,function(e){new t.SortableTable({table:e})});l=o.querySelectorAll('[data-module="moj-sortable-table"]');t.nodeListForEach(l,function(e){new t.SortableTable({table:e})})},AddAnother:function(t){this.container=$(t),this.container.data("moj-add-another-initialised")||(this.container.data("moj-add-another-initialised",!0),this.container.on("click",".moj-add-another__remove-button",$.proxy(this,"onRemoveButtonClick")),this.container.on("click",".moj-add-another__add-button",$.proxy(this,"onAddButtonClick")),this.container.find(".moj-add-another__add-button, moj-add-another__remove-button").prop("type","button"))}};return t.AddAnother.prototype.onAddButtonClick=function(t){var e=this.getNewItem();this.updateAttributes(this.getItems().length,e),this.resetItem(e);var o=this.getItems().first();this.hasRemoveButton(o)||this.createRemoveButton(o),this.getItems().last().after(e),e.find("input, textarea, select").first().focus()},t.AddAnother.prototype.hasRemoveButton=function(t){return t.find(".moj-add-another__remove-button").length},t.AddAnother.prototype.getItems=function(){return this.container.find(".moj-add-another__item")},t.AddAnother.prototype.getNewItem=function(){var t=this.getItems().first().clone();return this.hasRemoveButton(t)||this.createRemoveButton(t),t},t.AddAnother.prototype.updateAttributes=function(t,e){e.find("[data-name]").each(function(o,i){var n=i.id;i.name=$(i).attr("data-name").replace(/%index%/,t),i.id=$(i).attr("data-id").replace(/%index%/,t),($(i).siblings("label")[0]||$(i).parents("label")[0]||e.find('[for="'+n+'"]')[0]).htmlFor=i.id})},t.AddAnother.prototype.createRemoveButton=function(t){t.append('<button type="button" class="govuk-button govuk-button--secondary moj-add-another__remove-button">Remove</button>')},t.AddAnother.prototype.resetItem=function(t){t.find("[data-name], [data-id]").each(function(t,e){"checkbox"==e.type||"radio"==e.type?e.checked=!1:e.value=""})},t.AddAnother.prototype.onRemoveButtonClick=function(t){$(t.currentTarget).parents(".moj-add-another__item").remove();var e=this.getItems();1===e.length&&e.find(".moj-add-another__remove-button").remove(),e.each($.proxy(function(t,e){this.updateAttributes(t,$(e))},this)),this.focusHeading()},t.AddAnother.prototype.focusHeading=function(){this.container.find(".moj-add-another__heading").focus()},t.ButtonMenu=function(t){this.container=t.container,this.menu=this.container.find(".moj-button-menu__wrapper"),t.menuClasses&&this.menu.addClass(t.menuClasses),this.menu.attr("role","menu"),this.mq=t.mq,this.buttonText=t.buttonText,this.buttonClasses=t.buttonClasses||"",this.keys={esc:27,up:38,down:40,tab:9},this.menu.on("keydown","[role=menuitem]",$.proxy(this,"onButtonKeydown")),this.createToggleButton(),this.setupResponsiveChecks(),$(document).on("click",$.proxy(this,"onDocumentClick"))},t.ButtonMenu.prototype.onDocumentClick=function(t){$.contains(this.container[0],t.target)||this.hideMenu()},t.ButtonMenu.prototype.createToggleButton=function(){this.menuButton=$('<button class="govuk-button moj-button-menu__toggle-button '+this.buttonClasses+'" type="button" aria-haspopup="true" aria-expanded="false">'+this.buttonText+"</button>"),this.menuButton.on("click",$.proxy(this,"onMenuButtonClick")),this.menuButton.on("keydown",$.proxy(this,"onMenuKeyDown"))},t.ButtonMenu.prototype.setupResponsiveChecks=function(){this.mql=window.matchMedia(this.mq),this.mql.addListener($.proxy(this,"checkMode")),this.checkMode(this.mql)},t.ButtonMenu.prototype.checkMode=function(t){t.matches?this.enableBigMode():this.enableSmallMode()},t.ButtonMenu.prototype.enableSmallMode=function(){this.container.prepend(this.menuButton),this.hideMenu(),this.removeButtonClasses(),this.menu.attr("role","menu"),this.container.find(".moj-button-menu__item").attr("role","menuitem")},t.ButtonMenu.prototype.enableBigMode=function(){this.menuButton.detach(),this.showMenu(),this.addButtonClasses(),this.menu.removeAttr("role"),this.container.find(".moj-button-menu__item").removeAttr("role")},t.ButtonMenu.prototype.removeButtonClasses=function(){this.menu.find(".moj-button-menu__item").each(function(t,e){$(e).hasClass("govuk-button--secondary")&&($(e).attr("data-secondary","true"),$(e).removeClass("govuk-button--secondary")),$(e).hasClass("govuk-button--warning")&&($(e).attr("data-warning","true"),$(e).removeClass("govuk-button--warning")),$(e).removeClass("govuk-button")})},t.ButtonMenu.prototype.addButtonClasses=function(){this.menu.find(".moj-button-menu__item").each(function(t,e){"true"==$(e).attr("data-secondary")&&$(e).addClass("govuk-button--secondary"),"true"==$(e).attr("data-warning")&&$(e).addClass("govuk-button--warning"),$(e).addClass("govuk-button")})},t.ButtonMenu.prototype.hideMenu=function(){this.menuButton.attr("aria-expanded","false")},t.ButtonMenu.prototype.showMenu=function(){this.menuButton.attr("aria-expanded","true")},t.ButtonMenu.prototype.onMenuButtonClick=function(){this.toggle()},t.ButtonMenu.prototype.toggle=function(){"false"==this.menuButton.attr("aria-expanded")?(this.showMenu(),this.menu.find("[role=menuitem]").first().focus()):(this.hideMenu(),this.menuButton.focus())},t.ButtonMenu.prototype.onMenuKeyDown=function(t){switch(t.keyCode){case this.keys.down:this.toggle()}},t.ButtonMenu.prototype.onButtonKeydown=function(t){switch(t.keyCode){case this.keys.up:t.preventDefault(),this.focusPrevious(t.currentTarget);break;case this.keys.down:t.preventDefault(),this.focusNext(t.currentTarget);break;case this.keys.esc:this.mq.matches||(this.menuButton.focus(),this.hideMenu());break;case this.keys.tab:this.mq.matches||this.hideMenu()}},t.ButtonMenu.prototype.focusNext=function(t){var e=$(t).next();e[0]?e.focus():this.container.find("[role=menuitem]").first().focus()},t.ButtonMenu.prototype.focusPrevious=function(t){var e=$(t).prev();e[0]?e.focus():this.container.find("[role=menuitem]").last().focus()},t.FilterToggleButton=function(t){this.options=t,this.container=this.options.toggleButton.container,this.createToggleButton(),this.setupResponsiveChecks(),this.options.filter.container.attr("tabindex","-1"),this.options.startHidden&&this.hideMenu()},t.FilterToggleButton.prototype.setupResponsiveChecks=function(){this.mq=window.matchMedia(this.options.bigModeMediaQuery),this.mq.addListener($.proxy(this,"checkMode")),this.checkMode(this.mq)},t.FilterToggleButton.prototype.createToggleButton=function(){this.menuButton=$('<button class="govuk-button '+this.options.toggleButton.classes+'" type="button" aria-haspopup="true" aria-expanded="false">'+this.options.toggleButton.showText+"</button>"),this.menuButton.on("click",$.proxy(this,"onMenuButtonClick")),this.options.toggleButton.container.append(this.menuButton)},t.FilterToggleButton.prototype.checkMode=function(t){t.matches?this.enableBigMode():this.enableSmallMode()},t.FilterToggleButton.prototype.enableBigMode=function(){this.showMenu(),this.removeCloseButton()},t.FilterToggleButton.prototype.enableSmallMode=function(){this.hideMenu(),this.addCloseButton()},t.FilterToggleButton.prototype.addCloseButton=function(){this.options.closeButton&&(this.closeButton=$('<button class="moj-filter__close" type="button">'+this.options.closeButton.text+"</button>"),this.closeButton.on("click",$.proxy(this,"onCloseClick")),this.options.closeButton.container.append(this.closeButton))},t.FilterToggleButton.prototype.onCloseClick=function(){this.hideMenu(),this.menuButton.focus()},t.FilterToggleButton.prototype.removeCloseButton=function(){this.closeButton&&(this.closeButton.remove(),this.closeButton=null)},t.FilterToggleButton.prototype.hideMenu=function(){this.menuButton.attr("aria-expanded","false"),this.options.filter.container.addClass("moj-js-hidden"),this.menuButton.text(this.options.toggleButton.showText)},t.FilterToggleButton.prototype.showMenu=function(){this.menuButton.attr("aria-expanded","true"),this.options.filter.container.removeClass("moj-js-hidden"),this.menuButton.text(this.options.toggleButton.hideText)},t.FilterToggleButton.prototype.onMenuButtonClick=function(){this.toggle()},t.FilterToggleButton.prototype.toggle=function(){"false"==this.menuButton.attr("aria-expanded")?(this.showMenu(),this.options.filter.container.focus()):this.hideMenu()},t.FormValidator=function(t,e){this.form=t,this.errors=[],this.validators=[],$(this.form).on("submit",$.proxy(this,"onSubmit")),this.summary=e&&e.summary?$(e.summary):$(".govuk-error-summary"),this.originalTitle=document.title},t.FormValidator.entityMap={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;","/":"&#x2F;","`":"&#x60;","=":"&#x3D;"},t.FormValidator.prototype.escapeHtml=function(e){return String(e).replace(/[&<>"'`=\/]/g,function(e){return t.FormValidator.entityMap[e]})},t.FormValidator.prototype.resetTitle=function(){document.title=this.originalTitle},t.FormValidator.prototype.updateTitle=function(){document.title=this.errors.length+" errors - "+document.title},t.FormValidator.prototype.showSummary=function(){this.summary.html(this.getSummaryHtml()),this.summary.removeClass("moj-hidden"),this.summary.attr("aria-labelledby","errorSummary-heading"),this.summary.focus()},t.FormValidator.prototype.getSummaryHtml=function(){var t='<h2 id="error-summary-title" class="govuk-error-summary__title">There is a problem</h2>';t+='<div class="govuk-error-summary__body">',t+='<ul class="govuk-list govuk-error-summary__list">';for(var e=0,o=this.errors.length;e<o;e++){var i=this.errors[e];t+="<li>",t+='<a href="#'+this.escapeHtml(i.fieldName)+'">',t+=this.escapeHtml(i.message),t+="</a>",t+="</li>"}return t+="</ul>",t+="</div>"},t.FormValidator.prototype.hideSummary=function(){this.summary.addClass("moj-hidden"),this.summary.removeAttr("aria-labelledby")},t.FormValidator.prototype.onSubmit=function(t){this.removeInlineErrors(),this.hideSummary(),this.resetTitle(),this.validate()||(t.preventDefault(),this.updateTitle(),this.showSummary(),this.showInlineErrors())},t.FormValidator.prototype.showInlineErrors=function(){for(var t=0,e=this.errors.length;t<e;t++)this.showInlineError(this.errors[t])},t.FormValidator.prototype.showInlineError=function(e){var o=e.fieldName+"-error",i='<span class="govuk-error-message" id="'+o+'">'+this.escapeHtml(e.message)+"</span>",n=$("#"+e.fieldName),a=n.parents(".govuk-form-group"),s=a.find("label"),r=a.find("legend"),l=a.find("fieldset");a.addClass("govuk-form-group--error"),r.length?(r.after(i),a.attr("aria-invalid","true"),t.addAttributeValue(l[0],"aria-describedby",o)):(s.after(i),n.attr("aria-invalid","true"),t.addAttributeValue(n[0],"aria-describedby",o))},t.FormValidator.prototype.removeInlineErrors=function(){for(var t=0;t<this.errors.length;t++)this.removeInlineError(this.errors[t])},t.FormValidator.prototype.removeInlineError=function(e){var o=$("#"+e.fieldName).parents(".govuk-form-group");o.find(".govuk-error-message").remove(),o.removeClass("govuk-form-group--error"),o.find("[aria-invalid]").attr("aria-invalid","false");var i=e.fieldName+"-error";t.removeAttributeValue(o.find("[aria-describedby]")[0],"aria-describedby",i)},t.FormValidator.prototype.addValidator=function(t,e){this.validators.push({fieldName:t,rules:e,field:this.form.elements[t]})},t.FormValidator.prototype.validate=function(){this.errors=[];var t,e,o=null,i=!0;for(t=0;t<this.validators.length;t++)for(o=this.validators[t],e=0;e<o.rules.length;e++){if("boolean"==typeof(i=o.rules[e].method(o.field,o.rules[e].params))&&!i){this.errors.push({fieldName:o.fieldName,message:o.rules[e].message});break}if("string"==typeof i){this.errors.push({fieldName:i,message:o.rules[e].message});break}}return 0===this.errors.length},t.dragAndDropSupported()&&t.formDataSupported()&&t.fileApiSupported()&&(t.MultiFileUpload=function(t){this.defaultParams={uploadFileEntryHook:$.noop,uploadFileExitHook:$.noop,uploadFileErrorHook:$.noop,fileDeleteHook:$.noop,uploadStatusText:"Uploading files, please wait",dropzoneHintText:"Drag and drop files here or",dropzoneButtonText:"Choose files"},this.params=$.extend({},this.defaultParams,t),this.params.container.addClass("moj-multi-file-upload--enhanced"),this.feedbackContainer=this.params.container.find(".moj-multi-file__uploaded-files"),this.setupFileInput(),this.setupDropzone(),this.setupLabel(),this.setupStatusBox(),this.params.container.on("click",".moj-multi-file-upload__delete",$.proxy(this,"onFileDeleteClick"))},t.MultiFileUpload.prototype.setupDropzone=function(){this.fileInput.wrap('<div class="moj-multi-file-upload__dropzone" />'),this.dropzone=this.params.container.find(".moj-multi-file-upload__dropzone"),this.dropzone.on("dragover",$.proxy(this,"onDragOver")),this.dropzone.on("dragleave",$.proxy(this,"onDragLeave")),this.dropzone.on("drop",$.proxy(this,"onDrop"))},t.MultiFileUpload.prototype.setupLabel=function(){this.label=$('<label for="'+this.fileInput[0].id+'" class="govuk-button govuk-button--secondary">'+this.params.dropzoneButtonText+"</label>"),this.dropzone.append('<p class="govuk-body">'+this.params.dropzoneHintText+"</p>"),this.dropzone.append(this.label)},t.MultiFileUpload.prototype.setupFileInput=function(){this.fileInput=this.params.container.find(".moj-multi-file-upload__input"),this.fileInput.on("change",$.proxy(this,"onFileChange")),this.fileInput.on("focus",$.proxy(this,"onFileFocus")),this.fileInput.on("blur",$.proxy(this,"onFileBlur"))},t.MultiFileUpload.prototype.setupStatusBox=function(){this.status=$('<div aria-live="polite" role="status" class="govuk-visually-hidden" />'),this.dropzone.append(this.status)},t.MultiFileUpload.prototype.onDragOver=function(t){t.preventDefault(),this.dropzone.addClass("moj-multi-file-upload--dragover")},t.MultiFileUpload.prototype.onDragLeave=function(){this.dropzone.removeClass("moj-multi-file-upload--dragover")},t.MultiFileUpload.prototype.onDrop=function(t){t.preventDefault(),this.dropzone.removeClass("moj-multi-file-upload--dragover"),this.feedbackContainer.removeClass("moj-hidden"),this.status.html(this.params.uploadStatusText),this.uploadFiles(t.originalEvent.dataTransfer.files)},t.MultiFileUpload.prototype.uploadFiles=function(t){for(var e=0;e<t.length;e++)this.uploadFile(t[e])},t.MultiFileUpload.prototype.onFileChange=function(t){this.feedbackContainer.removeClass("moj-hidden"),this.status.html(this.params.uploadStatusText),this.uploadFiles(t.currentTarget.files),this.fileInput.replaceWith($(t.currentTarget).val("").clone(!0)),this.setupFileInput(),this.fileInput.focus()},t.MultiFileUpload.prototype.onFileFocus=function(t){this.label.addClass("moj-multi-file-upload--focused")},t.MultiFileUpload.prototype.onFileBlur=function(t){this.label.removeClass("moj-multi-file-upload--focused")},t.MultiFileUpload.prototype.getSuccessHtml=function(t){return'<span class="moj-multi-file-upload__success"> <svg class="moj-banner__icon" fill="currentColor" role="presentation" focusable="false" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 25 25" height="25" width="25"><path d="M25,6.2L8.7,23.2L0,14.1l4-4.2l4.7,4.9L21,2L25,6.2z"/></svg> '+t.messageHtml+"</span>"},t.MultiFileUpload.prototype.getErrorHtml=function(t){return'<span class="moj-multi-file-upload__error"> <svg class="moj-banner__icon" fill="currentColor" role="presentation" focusable="false" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 25 25" height="25" width="25"><path d="M13.6,15.4h-2.3v-4.5h2.3V15.4z M13.6,19.8h-2.3v-2.2h2.3V19.8z M0,23.2h25L12.5,2L0,23.2z"/></svg> '+t.message+"</span>"},t.MultiFileUpload.prototype.getFileRowHtml=function(t){var e="";return e+='<div class="govuk-summary-list__row moj-multi-file-upload__row">',e+='  <div class="govuk-summary-list__value moj-multi-file-upload__message">',e+='<span class="moj-multi-file-upload__filename">'+t.name+"</span>",e+='<span class="moj-multi-file-upload__progress">0%</span>',e+="  </div>",e+='  <div class="govuk-summary-list__actions moj-multi-file-upload__actions"></div>',e+="</div>"},t.MultiFileUpload.prototype.getDeleteButtonHtml=function(t){var e='<button class="moj-multi-file-upload__delete govuk-button govuk-button--secondary govuk-!-margin-bottom-0" type="button" name="delete" value="'+t.filename+'">';return e+='Delete <span class="govuk-visually-hidden">'+t.originalname+"</span>",e+="</button>"},t.MultiFileUpload.prototype.uploadFile=function(t){this.params.uploadFileEntryHook(this,t);var e=new FormData;e.append("documents",t);var o=$(this.getFileRowHtml(t));this.feedbackContainer.find(".moj-multi-file-upload__list").append(o),$.ajax({url:this.params.uploadUrl,type:"post",data:e,processData:!1,contentType:!1,success:$.proxy(function(e){e.error?(o.find(".moj-multi-file-upload__message").html(this.getErrorHtml(e.error)),this.status.html(e.error.message)):(o.find(".moj-multi-file-upload__message").html(this.getSuccessHtml(e.success)),this.status.html(e.success.messageText)),o.find(".moj-multi-file-upload__actions").append(this.getDeleteButtonHtml(e.file)),this.params.uploadFileExitHook(this,t,e)},this),error:$.proxy(function(e,o,i){this.params.uploadFileErrorHook(this,t,e,o,i)},this),xhr:function(){var t=new XMLHttpRequest;return t.upload.addEventListener("progress",function(t){if(t.lengthComputable){var e=t.loaded/t.total;e=parseInt(100*e,10),o.find(".moj-multi-file-upload__progress").text(" "+e+"%")}},!1),t}})},t.MultiFileUpload.prototype.onFileDeleteClick=function(t){t.preventDefault();var e=$(t.currentTarget),o={};o[e[0].name]=e[0].value,$.ajax({url:this.params.deleteUrl,type:"post",dataType:"json",data:o,success:$.proxy(function(t){t.error||(e.parents(".moj-multi-file-upload__row").remove(),0===this.feedbackContainer.find(".moj-multi-file-upload__row").length&&this.feedbackContainer.addClass("moj-hidden")),this.params.fileDeleteHook(this,t)},this)})}),t.MultiSelect=function(t){this.container=$(t.container),this.container.data("moj-multi-select-initialised")||(this.container.data("moj-multi-select-initialised",!0),this.toggle=$(this.getToggleHtml()),this.toggleButton=this.toggle.find("input"),this.toggleButton.on("click",$.proxy(this,"onButtonClick")),this.container.append(this.toggle),this.checkboxes=$(t.checkboxes),this.checkboxes.on("click",$.proxy(this,"onCheckboxClick")),this.checked=t.checked||!1)},t.MultiSelect.prototype.getToggleHtml=function(){return'<div class="govuk-checkboxes__item govuk-checkboxes--small moj-multi-select__checkbox">','  <input type="checkbox" class="govuk-checkboxes__input" id="checkboxes-all">','  <label class="govuk-label govuk-checkboxes__label moj-multi-select__toggle-label" for="checkboxes-all">','    <span class="govuk-visually-hidden">Select all</span>',"  </label>","</div>",'<div class="govuk-checkboxes__item govuk-checkboxes--small moj-multi-select__checkbox">  <input type="checkbox" class="govuk-checkboxes__input" id="checkboxes-all">  <label class="govuk-label govuk-checkboxes__label moj-multi-select__toggle-label" for="checkboxes-all">    <span class="govuk-visually-hidden">Select all</span>  </label></div>'},t.MultiSelect.prototype.onButtonClick=function(t){this.checked?(this.uncheckAll(),this.toggleButton[0].checked=!1):(this.checkAll(),this.toggleButton[0].checked=!0)},t.MultiSelect.prototype.checkAll=function(){this.checkboxes.each($.proxy(function(t,e){e.checked=!0},this)),this.checked=!0},t.MultiSelect.prototype.uncheckAll=function(){this.checkboxes.each($.proxy(function(t,e){e.checked=!1},this)),this.checked=!1},t.MultiSelect.prototype.onCheckboxClick=function(t){t.target.checked?this.checkboxes.filter(":checked").length===this.checkboxes.length&&(this.toggleButton[0].checked=!0,this.checked=!0):(this.toggleButton[0].checked=!1,this.checked=!1)},t.PasswordReveal=function(t){this.el=t,$el=$(this.el),$el.data("moj-password-reveal-initialised")||($el.data("moj-password-reveal-initialised",!0),$el.attr("spellcheck","false"),$el.wrap('<div class="moj-password-reveal"></div>'),this.container=$(this.el).parent(),this.createButton())},t.PasswordReveal.prototype.createButton=function(){this.button=$('<button type="button" class="govuk-button govuk-button--secondary moj-password-reveal__button">Show <span class="govuk-visually-hidden">password</span></button>'),this.container.append(this.button),this.button.on("click",$.proxy(this,"onButtonClick"))},t.PasswordReveal.prototype.onButtonClick=function(){"password"===this.el.type?(this.el.type="text",this.button.html('Hide <span class="govuk-visually-hidden">password</span>')):(this.el.type="password",this.button.html('Show <span class="govuk-visually-hidden">password</span>'))},"contentEditable"in document.documentElement&&(t.RichTextEditor=function(t){this.options=t,this.options.toolbar=this.options.toolbar||{bold:!1,italic:!1,underline:!1,bullets:!0,numbers:!0},this.textarea=this.options.textarea,this.container=$(this.textarea).parent(),this.container.data("moj-rich-text-editor-initialised")||(this.container.data("moj-rich-text-editor-initialised",!0),this.createToolbar(),this.hideDefault(),this.configureToolbar(),this.keys={left:37,right:39,up:38,down:40},this.container.on("click",".moj-rich-text-editor__toolbar-button",$.proxy(this,"onButtonClick")),this.container.find(".moj-rich-text-editor__content").on("input",$.proxy(this,"onEditorInput")),this.container.find("label").on("click",$.proxy(this,"onLabelClick")),this.toolbar.on("keydown",$.proxy(this,"onToolbarKeydown")))},t.RichTextEditor.prototype.onToolbarKeydown=function(t){var e;switch(t.keyCode){case this.keys.right:case this.keys.down:var o=(e=this.toolbar.find("button[tabindex=0]")).next("button");o[0]&&(o.focus(),e.attr("tabindex","-1"),o.attr("tabindex","0"));break;case this.keys.left:case this.keys.up:var i=(e=this.toolbar.find("button[tabindex=0]")).prev("button");i[0]&&(i.focus(),e.attr("tabindex","-1"),i.attr("tabindex","0"))}},t.RichTextEditor.prototype.getToolbarHtml=function(){var t="";return t+='<div class="moj-rich-text-editor__toolbar" role="toolbar">',this.options.toolbar.bold&&(t+='<button class="moj-rich-text-editor__toolbar-button moj-rich-text-editor__toolbar-button--bold" type="button" data-command="bold"><span class="govuk-visually-hidden">Bold</span></button>'),this.options.toolbar.italic&&(t+='<button class="moj-rich-text-editor__toolbar-button moj-rich-text-editor__toolbar-button--italic" type="button" data-command="italic"><span class="govuk-visually-hidden">Italic</span></button>'),this.options.toolbar.underline&&(t+='<button class="moj-rich-text-editor__toolbar-button moj-rich-text-editor__toolbar-button--underline" type="button" data-command="underline"><span class="govuk-visually-hidden">Underline</span></button>'),this.options.toolbar.bullets&&(t+='<button class="moj-rich-text-editor__toolbar-button moj-rich-text-editor__toolbar-button--unordered-list" type="button" data-command="insertUnorderedList"><span class="govuk-visually-hidden">Unordered list</span></button>'),this.options.toolbar.numbers&&(t+='<button class="moj-rich-text-editor__toolbar-button moj-rich-text-editor__toolbar-button--ordered-list" type="button" data-command="insertOrderedList"><span class="govuk-visually-hidden">Ordered list</span></button>'),t+="</div>"},t.RichTextEditor.prototype.getEnhancedHtml=function(t){return this.getToolbarHtml()+'<div class="govuk-textarea moj-rich-text-editor__content" contenteditable="true" spellcheck="false"></div>'},t.RichTextEditor.prototype.hideDefault=function(){this.textarea=this.container.find("textarea"),this.textarea.addClass("govuk-visually-hidden"),this.textarea.attr("aria-hidden",!0),this.textarea.attr("tabindex","-1")},t.RichTextEditor.prototype.createToolbar=function(){this.toolbar=document.createElement("div"),this.toolbar.className="moj-rich-text-editor",this.toolbar.innerHTML=this.getEnhancedHtml(),this.container.append(this.toolbar),this.toolbar=this.container.find(".moj-rich-text-editor__toolbar"),this.container.find(".moj-rich-text-editor__content").html(this.textarea.val())},t.RichTextEditor.prototype.configureToolbar=function(){this.buttons=this.container.find(".moj-rich-text-editor__toolbar-button"),this.buttons.prop("tabindex","-1"),this.buttons.first().prop("tabindex","0")},t.RichTextEditor.prototype.onButtonClick=function(t){document.execCommand($(t.currentTarget).data("command"),!1,null)},t.RichTextEditor.prototype.getContent=function(){return this.container.find(".moj-rich-text-editor__content").html()},t.RichTextEditor.prototype.onEditorInput=function(t){this.updateTextarea()},t.RichTextEditor.prototype.updateTextarea=function(){document.execCommand("defaultParagraphSeparator",!1,"p"),this.textarea.val(this.getContent())},t.RichTextEditor.prototype.onLabelClick=function(t){t.preventDefault(),this.container.find(".moj-rich-text-editor__content").focus()}),t.SearchToggle=function(t){if(this.options=t,this.options.search.container.data("moj-search-toggle-initialised"))return;this.options.search.container.data("moj-search-toggle-initialised",!0);this.toggleButton=$('<button class="moj-search-toggle__button" type="button" aria-haspopup="true" aria-expanded="false">'+this.options.toggleButton.text+'<svg viewBox="0 0 20 20" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" class="moj-search-toggle__button__icon"><path d="M7.433,12.5790048 C6.06762625,12.5808611 4.75763941,12.0392925 3.79217348,11.0738265 C2.82670755,10.1083606 2.28513891,8.79837375 2.28699522,7.433 C2.28513891,6.06762625 2.82670755,4.75763941 3.79217348,3.79217348 C4.75763941,2.82670755 6.06762625,2.28513891 7.433,2.28699522 C8.79837375,2.28513891 10.1083606,2.82670755 11.0738265,3.79217348 C12.0392925,4.75763941 12.5808611,6.06762625 12.5790048,7.433 C12.5808611,8.79837375 12.0392925,10.1083606 11.0738265,11.0738265 C10.1083606,12.0392925 8.79837375,12.5808611 7.433,12.5790048 L7.433,12.5790048 Z M14.293,12.579 L13.391,12.579 L13.071,12.269 C14.2300759,10.9245158 14.8671539,9.20813198 14.866,7.433 C14.866,3.32786745 11.5381325,-1.65045755e-15 7.433,-1.65045755e-15 C3.32786745,-1.65045755e-15 -1.65045755e-15,3.32786745 -1.65045755e-15,7.433 C-1.65045755e-15,11.5381325 3.32786745,14.866 7.433,14.866 C9.208604,14.8671159 10.9253982,14.2296624 12.27,13.07 L12.579,13.39 L12.579,14.294 L18.296,20 L20,18.296 L14.294,12.579 L14.293,12.579 Z"></path></svg></button>'),this.toggleButton.on("click",$.proxy(this,"onToggleButtonClick")),this.options.toggleButton.container.append(this.toggleButton)},t.SearchToggle.prototype.onToggleButtonClick=function(){"false"==this.toggleButton.attr("aria-expanded")?(this.toggleButton.attr("aria-expanded","true"),this.options.search.container.removeClass("moj-js-hidden"),this.options.search.container.find("input").first().focus()):(this.options.search.container.addClass("moj-js-hidden"),this.toggleButton.attr("aria-expanded","false"))},t.SortableTable=function(t){this.table=$(t.table),this.table.data("moj-search-toggle-initialised")||(this.table.data("moj-search-toggle-initialised",!0),this.setupOptions(t),this.body=this.table.find("tbody"),this.createHeadingButtons(),this.createStatusBox(),this.initialiseSortedColumn(),this.table.on("click","th button",$.proxy(this,"onSortButtonClick")))},t.SortableTable.prototype.setupOptions=function(t){t=t||{},this.statusMessage=t.statusMessage||"Sort by %heading% (%direction%)",this.ascendingText=t.ascendingText||"ascending",this.descendingText=t.descendingText||"descending"},t.SortableTable.prototype.createHeadingButtons=function(){for(var t,e=this.table.find("thead th"),o=0;o<e.length;o++)(t=$(e[o])).attr("aria-sort")&&this.createHeadingButton(t,o)},t.SortableTable.prototype.createHeadingButton=function(t,e){var o=t.text(),i=$('<button type="button" data-index="'+e+'">'+o+"</button>");t.text(""),t.append(i)},t.SortableTable.prototype.createStatusBox=function(){this.status=$('<div aria-live="polite" role="status" aria-atomic="true" class="govuk-visually-hidden" />'),this.table.parent().append(this.status)},t.SortableTable.prototype.initialiseSortedColumn=function(){var t=this.getTableRowsArray();this.table.find("th").filter('[aria-sort="ascending"], [aria-sort="descending"]').first().each((e,o)=>{var i=$(o).attr("aria-sort"),n=$(o).find("button").attr("data-index"),a=this.sort(t,n,i);this.addRows(a)})},t.SortableTable.prototype.onSortButtonClick=function(t){var e,o=t.currentTarget.getAttribute("data-index"),i=$(t.currentTarget).parent().attr("aria-sort");e="none"===i||"descending"===i?"ascending":"descending";var n=this.getTableRowsArray(),a=this.sort(n,o,e);this.addRows(a),this.removeButtonStates(),this.updateButtonState($(t.currentTarget),e)},t.SortableTable.prototype.updateButtonState=function(t,e){t.parent().attr("aria-sort",e);var o=this.statusMessage;o=(o=o.replace(/%heading%/,t.text())).replace(/%direction%/,this[e+"Text"]),this.status.text(o)},t.SortableTable.prototype.removeButtonStates=function(){this.table.find("thead th").attr("aria-sort","none")},t.SortableTable.prototype.addRows=function(t){for(var e=0;e<t.length;e++)this.body.append(t[e])},t.SortableTable.prototype.getTableRowsArray=function(){for(var t=[],e=this.body.find("tr"),o=0;o<e.length;o++)t.push(e[o]);return t},t.SortableTable.prototype.sort=function(t,e,o){return t.sort($.proxy(function(t,i){var n=$(t).find("td,th").eq(e),a=$(i).find("td,th").eq(e),s=this.getCellValue(n),r=this.getCellValue(a);return"ascending"===o?s<r?-1:s>r?1:0:r<s?-1:r>s?1:0},this))},t.SortableTable.prototype.getCellValue=function(t){var e=t.attr("data-sort-value");return e=e||t.html(),$.isNumeric(e)&&(e=parseInt(e,10)),e},t});