import mongoose from 'mongoose';
import IWaypointDocument from './IWaypointDocument'

const schema = new mongoose.Schema({
    _id: mongoose.Types.ObjectId,
    gid: String,
    direction: String,
    roads: [Object]
});

const Waypoint = mongoose.model<IWaypointDocument>("Waypoint", schema);

export default Waypoint