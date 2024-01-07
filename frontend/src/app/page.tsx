import styles from './page.module.css'
import UserList from "@/components/UserList";
import {createGrpcTransport} from "@connectrpc/connect-node";
import ProductList from "@/components/ProductList";


const transport = createGrpcTransport({
    baseUrl: "http://localhost:9093",
    httpVersion: "2",
    interceptors: []
})

export default async function Home() {
    return (
        <main className={styles.main}>
            <ProductList/>
            <UserList/>
        </main>
    )
}
