outlets = 2
var ringingNotes = []
var flag = 0

function sendNoteOn(note, velocity){
    outlet(1, velocity)
    outlet(0, note)
    ringingNotes.push(note)
}
function sendNoteOff(note){
    outlet(1, 0)
    outlet(0, note)
    var index = ringingNotes.indexOf(note)
    if(index !== -1){
        ringingNotes.splice(index, 1)
    }
}
function clearAllNotes(){
    for(var i = 0; i < ringingNotes.length; i++){
        sendNoteOff(ringingNotes[i])
    }
    ringingNotes = []
}

function slide(note, velocity) {
    if(velocity !== 0){
        sendNoteOn(note, velocity)
    }
    else{
        sendNoteOff(note)
    }
    post("ringingNotes: " + ringingNotes + "\n")

}

function bang(){
    flag = 1

    post("ringingNotes: " + ringingNotes + "\n")
    // //将ringingNotes中的音符加一发出

    // flag = 1
}