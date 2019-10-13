
var timer;

(()=>{
	document.addEventListener("DOMContentLoaded", ()=>{
		var converter = new showdown.Converter();

		var element = document.querySelector("#article_body_textarea");
		var md = element.value;
		document.querySelector("#preview-body").innerHTML = converter.makeHtml(md);

		element.addEventListener("keydown", (event)=>{
			clearTimeout(timer);
			timer = setTimeout(()=>{
				var md = element.value;
				document.querySelector("#preview-body").innerHTML = converter.makeHtml(md);
			},500);
		});

	})
})();