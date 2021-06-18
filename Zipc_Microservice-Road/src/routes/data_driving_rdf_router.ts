import express, { Request, Response } from "express";
import ParsingClient from 'sparql-http-client/ParsingClient';

/**
 * Router Definition
 */
export const dataDrivingRdfRouter = express.Router()

/**
 * RDF Client
 */
const rdfClient = new ParsingClient({ endpointUrl: 'http://172.16.0.94:3030/garden/sparql' });
// const rdfClient = new ParsingClient({ endpointUrl: 'http://localhost:3030/test03/sparql' });
const sparqlQuery = (tag: any) => `
prefix : <http://www.zipc.com/model/scenario#>
prefix owl: <http://www.w3.org/2002/07/owl#>
prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
prefix xml: <http://www.w3.org/XML/1998/namespace>
prefix xsd: <http://www.w3.org/2001/XMLSchema#>
prefix zss: <http://www.zipc.com/schema/scenario-schema#>
prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
base <http://www.zipc.com/model/scenario>

SELECT ?tableName ?nameSpace ?startTime ?endTime ?tagName WHERE {
  GRAPH <http://www.zipc.com/model/scenario> {
    ?scenario a zss:RawData ;
              rdfs:label ?tableName ;
              zss:namespace ?nameSpace .
    ?scenario zss:tag ?tag .
    ?tag a zss:Tag ;
         zss:start [ rdfs:label ?startTime ] ;
         zss:end [ rdfs:label ?endTime ] ;
         rdfs:label ?tagName 
    FILTER REGEX (?tagName, "${tag}", "i").
  }
}
`;

/**
 * REST API
 * Controller Definitions
 */

// GET data/driving/rdf/
dataDrivingRdfRouter.get('/', async function (req: Request, res: Response) {
    try {
        // TODO
        res.sendStatus(405);
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// GET data/driving/rdf/list/:conditions
dataDrivingRdfRouter.get('/list/:conditions', async function (req: Request, res: Response) {
    const tags = req.query.tags;
    // const cond01 = req.query.cond01;
    // const cond02 = req.query.cond02;
    // const cond03 = req.query.cond03;
    try {
        let resultData: any[] = [];
        const bindings = await rdfClient.query.select(sparqlQuery(!tags ? '.*' : tags))
        for (let row of bindings) {
            resultData.push({
                'rawData': {
                    'nameSpace': row.nameSpace.value,
                    'tableName': row.tableName.value,
                    'startTime': row.startTime.value,
                    'endTime': row.endTime.value,
                },
                'title': row.tableName.value,
                'description': `<span style="font-weight: bold;">Tag</span>: ${row.tagName.value}, <span style="font-weight: bold;">Start Time</span>: ${row.startTime.value}, <span style="font-weight: bold;">End Time</span>: ${row.endTime.value}`
            })
        }
        res.status(200).send(JSON.stringify(resultData));
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// POST data/driving/rdf/
dataDrivingRdfRouter.post("/", async (req: Request, res: Response) => {
    try {
        // TODO
        res.sendStatus(405);
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// PUT data/driving/rdf/
dataDrivingRdfRouter.put("/", async (req: Request, res: Response) => {
    try {
        // TODO
        res.sendStatus(405);
    } catch (e) {
        res.status(500).send(e.message);
    }
});

// DELETE data/driving/rdf/:keywords
dataDrivingRdfRouter.delete("/:keywords", async (req: Request, res: Response) => {
    const keywords: string = req.params.keywords;
    try {
        // TODO
        res.sendStatus(405);
    } catch (e) {
        res.status(500).send(e.message);
    }
});

