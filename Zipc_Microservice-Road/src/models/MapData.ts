import mongoose from 'mongoose';
import IMapDataDocument from './IMapDataDocument'


const schema = new mongoose.Schema({
    _id: mongoose.Types.ObjectId,
    gid: String,
    description: String,
    n: Number,
    e: Number,
    s: Number,
    w: Number,
    data: Object
});

const MapData = mongoose.model<IMapDataDocument>("MapData", schema);

export default MapData