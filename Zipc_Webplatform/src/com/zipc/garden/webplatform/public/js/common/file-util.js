function FileUtil(){
    this.folderPaths = [];
    this.filePaths = [];
    this.fileBytes = [];
    
    this.convertToByte = async function(dataTransfer){
        const items = dataTransfer.items;
        const entrys = [];
        const promise = [];
        for (let item of items) {
            const entry = item.webkitGetAsEntry();
            promise.push(scanFiles(entry, entrys, this.folderPaths));
        }
        
        await Promise.all(promise);
        promise.length = 0;

        for(let entry of entrys) {
            promise.push(readFile(entry, this.filePaths, this.fileBytes));
        }
        await Promise.all(promise);
    }
}

async function readFile (entry, filePaths, fileBytes) {
    const entries = await new Promise(resolve => {
        entry.file(function(file) {
            const fileReader = new FileReader();
            fileReader.onload = function (e) {
                var arrayBuffer = e.target.result;
                var bytes = new Uint8Array(arrayBuffer);
                var byteArray = Array.from(bytes);
                filePaths.push(entry.fullPath);
                fileBytes.push(byteArray);
                resolve();
            }
            fileReader.readAsArrayBuffer(file);
        });
    });
}

async function scanFiles(entry, tmpObject, folderPaths) {
    if (entry.isDirectory) {
        folderPaths.push(entry.fullPath);
        const entryReader = entry.createReader();
        const entries = await new Promise(resolve => {
            entryReader.readEntries(entries => resolve(entries));
        });
        await Promise.all(entries.map(entry => scanFiles(entry, tmpObject, folderPaths)));
    } else {
        tmpObject.push(entry);
    }
}
