
var previewTimer, growTimer;

(()=>{
	document.addEventListener("DOMContentLoaded", ()=>{
		var converter = new showdown.Converter();

		var taBody = document.querySelector("#article_body_textarea");
		var md = taBody.value;
		document.querySelector("#preview-body").innerHTML = converter.makeHtml(md);

		taBody.addEventListener("keydown", (event)=>{
			
			// Preview generation
			clearTimeout(previewTimer);
			previewTimer = setTimeout(()=>{
				var md = taBody.value;
				document.querySelector("#preview-body").innerHTML = converter.makeHtml(md);
			},500);
			
			// Resize on edit
			delayedResizeTextarea(taBody);
		});
		
		taBody.addEventListener("change", ()=>{ delayedResizeTextarea(taBody);});
		taBody.addEventListener("cut", ()=>{ delayedResizeTextarea(taBody);});
		taBody.addEventListener("paste", ()=>{ delayedResizeTextarea(taBody);});
		taBody.addEventListener("drop", ()=>{ delayedResizeTextarea(taBody);});
		//resizeTextarea(taBody);
		
		var taIngress = document.querySelector("#article_ingress_textarea");
		taIngress.addEventListener("keydown", ()=>{ delayedResizeTextarea(taIngress);});
		taIngress.addEventListener("change", ()=>{ delayedResizeTextarea(taIngress);});
		taIngress.addEventListener("cut", ()=>{ delayedResizeTextarea(taIngress);});
		taIngress.addEventListener("paste", ()=>{ delayedResizeTextarea(taIngress);});
		taIngress.addEventListener("drop", ()=>{ delayedResizeTextarea(taIngress);});
		//resizeTextarea(taIngress);
	});
})();

function resizeTextarea(element){
	element.style.height = 'auto';
	element.style.height = element.scrollHeight + 'px';
}

function delayedResizeTextarea(element){
	setTimeout( ()=>{ resizeTextarea(element); }, 0);
}