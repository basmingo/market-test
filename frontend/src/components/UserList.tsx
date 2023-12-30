'use client'
import {useEffect, useState} from "react";
import {red} from "next/dist/lib/picocolors";


export default function UserList() {
    const [users, setUsers] = useState([{}])

    useEffect(() => {
            fetch("http://localhost:8080/users/search")
                .then(r => r.json())
                .then(r => setUsers(r))
        }
    )

    return (
        <div>
            {users.length != 0 ? users.map(user => <User value={user}/>) : "No users found"}
        </div>
    )
}

const User = (userInfo: any) => {
    return (<div style={{color: "red", margin: "40px", border: "1px solid", padding: "12px", borderRadius: "5px", boxShadow: "5px 5px 10px red"}}>
        <div> {userInfo.value.name} {userInfo.value.lastName} </div>
        <div> Age: {userInfo.value.age} </div>
        <div> Balance: {userInfo.value.balance} </div>
    </div>)
}
