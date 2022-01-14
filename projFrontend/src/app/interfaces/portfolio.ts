import { User } from './user';

export interface Portfolio {
  id: number,
  name: string,
  public_key: string,

  users: User[]
}