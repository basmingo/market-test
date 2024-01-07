'use client'

import {Dispatch, SetStateAction, useEffect, useState} from "react";
import styles from "./component.module.css"

export default function CreateUser() {
    const [username, setUsername] = useState("")
    const [lastName, setLastName] = useState("")
    const [balance, setBalance] = useState("")
    const [age, setAge] = useState(0)

    return (
        <div className={styles.block}>
            <InputInfo callback={(message: string) => setUsername(message)} placeholder={"Name"}/>
            <InputInfo callback={(message: string) => setLastName(message)} placeholder={"Last name"}/>
            <InputInfo callback={(message: number) => setAge(message)} placeholder={"Age"}/>
            <InputInfo callback={(message: string) => setBalance(message)} placeholder={"Balance"}/>
            <button className={styles.std_button}
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
    return <div className={styles.create_user_form}>
        <input
            placeholder={props.placeholder}
            className={styles.input_form}
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
