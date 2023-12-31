import styles from './page.module.css'
import UserList from "@/components/UserList";
import { createPromiseClient } from "@connectrpc/connect";
import {UserService} from "../../../proto/build/bufbuild/generated/gen/UserService_connect";
import { createGrpcTransport } from "@connectrpc/connect-node";
import CreateUser from "@/components/CreateUser";


const transport = createGrpcTransport({
    baseUrl: "http://localhost:9093",
    httpVersion: "2",
    interceptors: []
})

export default async function Home() {
    return (
        <main className={styles.main}>
            <div>
                <UserList/>
                <CreateUser />
            </div>
            {/*<div className={styles.description}>*/}
            {/*</div>*/}
        </main>
    )
}
