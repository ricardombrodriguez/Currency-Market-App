import { User } from "./user";

export interface Extension {

    id: number,
    name: string,
    description: string,
    path: string,
    user: User

}
