'use client'

import {Dispatch, SetStateAction, useEffect, useState} from "react";

export default function CreateUser() {
    const [name, setName] = useState()

    return (
        <div style={{color: "red", border: "1px solid", padding: "15px"}}>
            <div> name: <InputInfo/></div>
            <div> last name:</div>
            <div> age:</div>
            <div> balance:</div>
            <button style={{color: "white", background: "red", padding: "5px", border: "1px solid"}}
                    onClick={() => create()}> CREATE
            </button>
        </div>
    )
}

function create() {
    const rb =

        fetch("http://localhost:8080/users", {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: "POST",
                body: JSON.stringify(
                    {
                        "age": 1488,
                        "balance": "100000",
                        "lastName": "Valya",
                        "name": "Golubeva"
                    }
                )
            }
        ).then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                response.json().then(function (data) {
                    console.log(data);
                });
            }
        ).catch(function (err) {
            console.log('Fetch Error :-S', err);
        })
}

const InputInfo = () => {
    return <input/>
}

function a() {
    console.log("!")
}