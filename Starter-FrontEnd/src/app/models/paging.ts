import { Barang } from './barang';
export class Paging {
    content: Barang;
    totalPages: number;
    totalElements: number;
    last: boolean;
    size: number;
    number: number;
    sort: string;
    first: boolean;
    numberOfElements: number;
}