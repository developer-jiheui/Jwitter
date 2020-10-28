import React from "react";
import Input from "@material-ui/core/Input";

function InputWithCounter({name, inputType, placeHolder, maxChar, multiline, rows}) {
    const [ numChar, setNumChar ] = React.useState("0");

    function countChar(event){
        console.log(event)
        setNumChar(event.target.value.length);
    }

    return (
        <div>
        <textarea name="name"
               type={inputType}
               placeholder={placeHolder}
               maxLength={maxChar}
               fullWidth
               className="edit-profile-input"
               onChange={countChar}
               multiline={multiline}
               rows={rows}
        />
        <span class="charcount-indicator">
           {numChar}/{maxChar}
        </span>
        </div>
    )
}

export default InputWithCounter