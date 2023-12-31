'use client'

import {Dispatch, SetStateAction, useEffect, useState} from "react";

export default function CreateUser() {
    const [username, setUsername] = useState("")
    const [lastName, setLastName] = useState("")
    const [balance, setBalance] = useState("")
    const [age, setAge] = useState(0)

    return (
        <div style={{color: "red", border: "1px solid", padding: "15px", margin: "20px"}}>
            <InputInfo callback={(message: string) => setUsername(message)} placeholder={"Name"}/>
            <InputInfo callback={(message: string) => setLastName(message)} placeholder={"Last name"}/>
            <InputInfo callback={(message: number) => setAge(message)} placeholder={"Age"}/>
            <InputInfo callback={(message: string) => setBalance(message)} placeholder={"Balance"}/>
            <button style={{color: "white", background: "red", padding: "5px", border: "1px solid", margin: "5px"}}
                    onClick={() => create({
                        "name": username,
                        "lastName": lastName,
                        "balance": balance,
                        "age": age
                    })}> CREATE
            </button>
        </div>
    )
}

function create(user: User) {
    fetch("http://localhost:8080/users", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(user)
        }
    )
}

const InputInfo = (props: Prop) => {
    return <div style={{display: "flex", flexWrap: "nowrap", justifyContent: "space-between", margin: "5px"}}>
        <input
            placeholder={props.placeholder}
            style={{color: "red", width: "100%", border: "0", borderBottom: "1px solid red", background: "transparent"}}
            onChange={value => props.callback(value.target.value)}
        />
    </div>
}

interface User {
    name: string
    lastName: string
    balance: string
    age: number
}

interface Prop {
    placeholder: string;
    callback: (text: any) => void;
}
