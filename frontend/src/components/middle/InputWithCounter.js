import React from "react";

function InputWithCounter({name, inputType, placeHolder, maxChar, multiline, rows, value}) {
    const [ numChar, setNumChar ] = React.useState(value ? value.length : 0);

    function countChar(event){
        setNumChar(event.target.value.length);
    }

    return (
        <div>
        <textarea name="name"
               type={inputType}
               placeholder={placeHolder}
               maxLength={maxChar}
               className="edit-profile-input"
               onChange={countChar}
               multiline={multiline}
               rows={rows}
               defaultValue={value}/>
        <span className="charcount-indicator">
           {numChar}/{maxChar}
        </span>
        </div>
    )
}

export default InputWithCounter