import { Coin } from "./coin";

export interface Wallet extends Coin {

    volume: number,
    quantity: number

}