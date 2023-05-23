function load(event) {
    var file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            const content = e.target.result;
            const textarea = document.getElementById("stb")
            textarea.value = content;
        }
        reader.readAsText(file);
    }
}