import { Paging } from './paging';
export class Hateoas {
    content: Paging;
    _links: Self;
}

export class Self {
    href: string;
}