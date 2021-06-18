import mongoose from 'mongoose'

export default interface IWaypointDocument extends mongoose.Document {
    _id: mongoose.Types.ObjectId
    gid: string
    direction: string
    roads: [typeof Object]
}