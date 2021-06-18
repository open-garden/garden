export type RunDataRow = {
  id: number;
  measurement: string;
  mapid: string;
};

export type RDFWhereClause = {
  tag: string[];
  measurement: string[];
  startTime: string[];
  endTime: string[];
};

export type RDFSearchResult = {
  tag: string;
  measurement: string;
  startTime: string;
  endTime: string;
};
