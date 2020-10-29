import React from "react";

function InputWithCounter({name, inputType, placeHolder, maxChar, multiline, rows, value, updateMethod}) {
    const [ numChar, setNumChar ] = React.useState(value ? value.length : 0);

    function countChar(event){
        setNumChar(event.target.value.length);
    }

    function handleInputChange(e) {
        countChar(e);
        updateMethod(e);
    }

    return (
        <div>
        <textarea name={name}
               type={inputType}
               placeholder={placeHolder}
               maxLength={maxChar}
               className="edit-profile-input"
               multiline={multiline}
               rows={rows}
               defaultValue={value}
               onChange={handleInputChange}/>
        <span className="charcount-indicator">
           {numChar}/{maxChar}
        </span>
        </div>
    )
}

export default InputWithCounter