import mongoose from 'mongoose'

export default interface IMapDataDocument extends mongoose.Document {
    _id: mongoose.Types.ObjectId
    gid: string
    description: string
    n: number
    e: number
    s: number
    w: number
    data: Object
}