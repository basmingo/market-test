'use client'
import {useEffect, useState} from "react";
import styles from "./component.module.css"
import CreateUser from "@/components/CreateUser";

export default function ProductList() {
    const [product, setProduct]: [Product[], any] = useState([])

    useEffect(() => {
            fetch("http://pbasmanov.example.com/api/products/search")
                .then(r => r.json())
                .then(r => setProduct(r))
        }
    )

    return (
        <div>
            <div className={styles.block}>
                <div style={{margin: "5px"}}> PRODUCTS DATABASE</div>
                {product.length != 0 ? product.map(product => <Product value={product}/>) : "No products found"}
            </div>
            {/*<CreateUser/>*/}
        </div>
    )
}

const Product = (product: any) => {
    return (<div className={styles.warehouse}>
        <div> {product.value.displayName} {product.value.lastName} {product.value.status} </div>
    </div>)
}

interface Product {
    productId: string;
    displayName: string;
    created: string;
    updated: string;
    status: string;
}
