'use client'
import {useEffect, useState} from "react";
import styles from "./component.module.css"
import CreateUser from "@/components/CreateUser";

export default function UserList() {
    const [users, setUsers] = useState([{}])

    useEffect(() => {
            fetch("http://pbasmanov.example.com/api/users/search")
                .then(r => r.json())
                .then(r => setUsers(r))
        }
    )

    return (
        <div>
            <div className={styles.block}>
                <div style={{margin: "5px"}}> USERS DATABASE</div>
                {users.length != 0 ? users.map(user => <User value={user}/>) : "No users found"}
            </div>
            <CreateUser/>
        </div>
    )
}

const User = (userInfo: any) => {
    return (<div className={styles.info_table}>
        <div> {userInfo.value.name} {userInfo.value.lastName} </div>
        <div> Age: {userInfo.value.age} </div>
        <div> Balance: {userInfo.value.balance} </div>
    </div>)
}
