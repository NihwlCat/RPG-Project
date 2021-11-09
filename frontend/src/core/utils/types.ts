
export type Profile = {
    name: string;
    age: number;
    alignment: string;
    status: string;
    imgUrl: string;
    statusVerbose: string;
}

type Individuality = {
    value: number;
    description: string;
}

type Basic = {
    life: number;
    maxLife: number;
    magic: number;
    maxMagic: number;
    sanity: number;
    maxSanity: number;
    awakening: number;
    control: number;
    movement: number;
    vigor: number;
    defense: number;
}

export type Spell = {
    name: string;
    description: string;
    imgUrl: string;
}

export type Item = {
    id: number;
    description: string;
    type: string;
    stringType: string;
}

export type Character = {
    id: string;
    profile: Profile;
    individuality: Individuality;
    basic: Basic;
    seals: Spell[];
}

export type Hash = {
    [key: string]: number;
}

export type Sheet = {
    id: string;
    basic: Hash;
    general: Hash;
    offensive: Hash;
}