const express = require("express");
const app = express();
app.use(express.json({ limit: "50mb" }));
app.use(express.urlencoded({ limit: "50mb", extended: true }));
app.use("/scenario_editor", express.static("scenario_editor"));
var MongoClient = require("mongodb").MongoClient;
const ObjectID = require("mongodb").ObjectID;
const ejs = require("ejs");

app.get("/scenario_editor/api/getFunctionalListByRange", (req, res) => {
  let functionsList = [];
  let req_skip = Number(req.query["skip"]);
  let req_limit = Number(req.query["limit"]);
  let req_filter_title = "";
  let req_filter_lsid = "";
  let ref_id = "";

  for (const key in req.query) {
    switch (key) {
      case "title":
        req_filter_title = req.query[key];
        break;
      case "lsid":
        req_filter_lsid = req.query[key];
        break;
    }
  }

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      if (req_filter_title !== undefined && req_filter_title !== "") {
        if (req_filter_lsid !== undefined && req_filter_lsid !== "") {
          dbo
            .collection("logicalScenario")
            .findOne({ gid: req_filter_lsid }, function (err, lg_result) {
              if (err) throw err;
              if (lg_result !== null) {
                ref_id = lg_result["func_ref"];
                let title_reg_filter = {
                  "data.title": new RegExp(".*" + req_filter_title + ".*"),
                  _id: new ObjectID(ref_id),
                };
                dbo
                  .collection("functionalScenario")
                  .find(title_reg_filter)
                  .count(function (err, allcount) {
                    if (err) throw err;
                    if (allcount === null) {
                      functionsList = { allcount: 0 };
                      functionsList.result = [];
                      res.json(functionsList);
                    } else {
                      if (req_skip < 0) {
                        if (allcount > req_limit) {
                          req_skip =
                            Math.floor(allcount / req_limit) * req_limit;
                        } else {
                          req_skip = 0;
                        }
                      }
                      dbo
                        .collection("functionalScenario")
                        .find(title_reg_filter, {
                          skip: req_skip,
                          limit: req_limit,
                        })
                        .toArray(function (err, fn_result) {
                          if (err) throw err;
                          if (fn_result === null) {
                            functionsList = { allcount: 0 };
                            functionsList.result = [];
                            res.json(functionsList);
                          } else {
                            functionsList = { allcount: allcount };
                            functionsList.result = fn_result;
                            res.json(functionsList);
                          }
                        });
                    }
                  });
              } else {
                functionsList = { allcount: 0 };
                functionsList.result = [];
                res.json(functionsList);
              }
            });
        } else {
          let title_reg_filter = {
            "data.title": new RegExp(".*" + req_filter_title + ".*"),
          };
          dbo
            .collection("functionalScenario")
            .find(title_reg_filter)
            .count(function (err, allcount) {
              if (err) throw err;
              if (allcount === null) {
                functionsList = { allcount: 0 };
                functionsList.result = [];
                res.json(functionsList);
              } else {
                if (req_skip < 0) {
                  if (allcount > req_limit) {
                    req_skip = Math.floor(allcount / req_limit) * req_limit;
                  } else {
                    req_skip = 0;
                  }
                }
                dbo
                  .collection("functionalScenario")
                  .find(title_reg_filter, { skip: req_skip, limit: req_limit })
                  .toArray(function (err, fn_result) {
                    if (err) throw err;
                    if (fn_result === null) {
                      functionsList = { allcount: 0 };
                      functionsList.result = [];
                      res.json(functionsList);
                    } else {
                      functionsList = { allcount: allcount };
                      functionsList.result = fn_result;
                      res.json(functionsList);
                    }
                  });
              }
            });
        }
      } else {
        if (req_filter_lsid === undefined || req_filter_lsid === "") {
          dbo
            .collection("functionalScenario")
            .find({})
            .count(function (err, allcount) {
              if (err) throw err;
              if (allcount === null) {
                functionsList = { allcount: 0 };
                functionsList.result = [];
                res.json(functionsList);
              } else {
                if (req_skip < 0) {
                  if (allcount > req_limit) {
                    let mod = allcount % req_limit;
                    if (mod === 0) {
                      req_skip =
                        (Math.floor(allcount / req_limit) - 1) * req_limit;
                    } else {
                      req_skip = Math.floor(allcount / req_limit) * req_limit;
                    }
                  } else {
                    req_skip = 0;
                  }
                }
                dbo
                  .collection("functionalScenario")
                  .find({}, { skip: req_skip, limit: req_limit })
                  .toArray(function (err, fn_result) {
                    if (err) throw err;
                    if (fn_result === null) {
                      functionsList = { allcount: 0 };
                      functionsList.result = [];
                      res.json(functionsList);
                    } else {
                      functionsList = { allcount: allcount };
                      functionsList.result = fn_result;
                      res.json(functionsList);
                    }
                  });
              }
            });
        } else {
          dbo
            .collection("logicalScenario")
            .findOne({ gid: req_filter_lsid }, function (err, result) {
              if (err) throw err;
              if (result !== null) {
                ref_id = result["func_ref"];
                dbo
                  .collection("functionalScenario")
                  .find({ _id: new ObjectID(ref_id) })
                  .count(function (err, allcount) {
                    if (err) throw err;
                    if (allcount === null) {
                      functionsList = { allcount: 0 };
                      functionsList.result = [];
                      res.json(functionsList);
                    } else {
                      if (req_skip < 0) {
                        if (allcount > req_limit) {
                          let mod = allcount % req_limit;
                          if (mod === 0) {
                            req_skip =
                              (Math.floor(allcount / req_limit) - 1) *
                              req_limit;
                          } else {
                            req_skip =
                              Math.floor(allcount / req_limit) * req_limit;
                          }
                        } else {
                          req_skip = 0;
                        }
                      }
                      dbo
                        .collection("functionalScenario")
                        .find(
                          { _id: new ObjectID(ref_id) },
                          { skip: req_skip, limit: req_limit }
                        )
                        .toArray(function (err, fn_result) {
                          if (err) throw err;
                          if (fn_result === null) {
                            functionsList = { allcount: 0 };
                            functionsList.result = [];
                            res.json(functionsList);
                          } else {
                            functionsList = { allcount: allcount };
                            functionsList.result = fn_result;
                            res.json(functionsList);
                          }
                        });
                    }
                  });
              } else {
                functionsList = { allcount: 0 };
                functionsList.result = [];
                res.json(functionsList);
              }
            });
        }
      }
    }
  );
});

app.get("/scenario_editor/api/getLogicalListByRange", (req, res) => {
  let logicalList = [];
  let req_skip = Number(req.query["skip"]);
  let req_limit = Number(req.query["limit"]);
  let req_filter_fnid = "";
  let req_filter_cnid = "";
  let fn_ref_id = "";
  let cn_ref_id = "";

  for (const key in req.query) {
    switch (key) {
      case "fnid":
        req_filter_fnid = req.query[key];
        break;
      case "cnid":
        req_filter_cnid = req.query[key];
        break;
    }
  }

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      if (req_filter_fnid !== undefined && req_filter_fnid !== "") {
        if (req_filter_cnid !== undefined && req_filter_cnid !== "") {
          dbo
            .collection("concreteScenario")
            .findOne({ gid: req_filter_cnid }, function (err, cn_result) {
              if (err) throw err;
              if (cn_result === null) {
                logicalList = { allcount: 0 };
                logicalList.result = [];
                res.json(logicalList);
              } else {
                dbo
                  .collection("functionalScenario")
                  .findOne({ gid: req_filter_fnid }, function (err, fn_result) {
                    if (err) throw err;
                    if (fn_result === null) {
                      logicalList = { allcount: 0 };
                      logicalList.result = [];
                      res.json(logicalList);
                    } else {
                      cn_ref_id = cn_result["logic_ref"];
                      fn_ref_id = fn_result["_id"];
                      let lg_filter = {
                        func_ref: fn_ref_id,
                        _id: new ObjectID(cn_ref_id),
                      };
                      dbo
                        .collection("logicalScenario")
                        .find(lg_filter)
                        .count(function (err, allcount) {
                          if (allcount === null) {
                            logicalList = { allcount: 0 };
                            logicalList.result = [];
                            res.json(logicalList);
                          } else {
                            if (req_skip < 0) {
                              if (allcount > req_limit) {
                                req_skip =
                                  Math.floor(allcount / req_limit) * req_limit;
                              } else {
                                req_skip = 0;
                              }
                            }
                            dbo
                              .collection("logicalScenario")
                              .find(lg_filter, {
                                skip: req_skip,
                                limit: req_limit,
                              })
                              .toArray(function (err, lg_result) {
                                if (err) throw err;
                                if (lg_result === null) {
                                  logicalList = { allcount: 0 };
                                  logicalList.result = [];
                                  res.json(logicalList);
                                } else {
                                  logicalList = { allcount: allcount };
                                  logicalList.result = lg_result;
                                  res.json(logicalList);
                                }
                              });
                          }
                        });
                    }
                  });
              }
            });
        } else {
          dbo
            .collection("functionalScenario")
            .findOne({ gid: req_filter_fnid }, function (err, fn_result) {
              if (err) throw err;
              if (fn_result === null) {
                logicalList = { allcount: 0 };
                logicalList.result = [];
                res.json(logicalList);
              } else {
                fn_ref_id = fn_result["_id"];
                let lg_filter = { func_ref: fn_ref_id };
                dbo
                  .collection("logicalScenario")
                  .find(lg_filter)
                  .count(function (err, allcount) {
                    if (allcount === null) {
                      logicalList = { allcount: 0 };
                      logicalList.result = [];
                      res.json(logicalList);
                    } else {
                      if (req_skip < 0) {
                        if (allcount > req_limit) {
                          req_skip =
                            Math.floor(allcount / req_limit) * req_limit;
                        } else {
                          req_skip = 0;
                        }
                      }
                      dbo
                        .collection("logicalScenario")
                        .find(lg_filter, { skip: req_skip, limit: req_limit })
                        .toArray(function (err, lg_result) {
                          if (err) throw err;
                          if (lg_result === null) {
                            logicalList = { allcount: 0 };
                            logicalList.result = [];
                            res.json(logicalList);
                          } else {
                            logicalList = { allcount: allcount };
                            logicalList.result = lg_result;
                            res.json(logicalList);
                          }
                        });
                    }
                  });
              }
            });
        }
      } else {
        if (req_filter_cnid === undefined || req_filter_cnid === "") {
          let lg_filter = {};
          dbo
            .collection("logicalScenario")
            .find(lg_filter)
            .count(function (err, allcount) {
              if (allcount === null) {
                logicalList = { allcount: 0 };
                logicalList.result = [];
                res.json(logicalList);
              } else {
                if (req_skip < 0) {
                  if (allcount > req_limit) {
                    req_skip = Math.floor(allcount / req_limit) * req_limit;
                  } else {
                    req_skip = 0;
                  }
                }
                dbo
                  .collection("logicalScenario")
                  .find(lg_filter, { skip: req_skip, limit: req_limit })
                  .toArray(function (err, lg_result) {
                    if (err) throw err;
                    if (lg_result === null) {
                      logicalList = { allcount: 0 };
                      logicalList.result = [];
                      res.json(logicalList);
                    } else {
                      logicalList = { allcount: allcount };
                      logicalList.result = lg_result;
                      res.json(logicalList);
                    }
                  });
              }
            });
        } else {
          dbo
            .collection("concreteScenario")
            .findOne({ gid: req_filter_cnid }, function (err, cn_result) {
              if (err) throw err;
              if (cn_result === null) {
                logicalList = { allcount: 0 };
                logicalList.result = [];
                res.json(logicalList);
              } else {
                cn_ref_id = cn_result["logic_ref"];
                let lg_filter = { _id: new ObjectID(cn_ref_id) };
                dbo
                  .collection("logicalScenario")
                  .find(lg_filter)
                  .count(function (err, allcount) {
                    if (allcount === null) {
                      logicalList = { allcount: 0 };
                      logicalList.result = [];
                      res.json(logicalList);
                    } else {
                      if (req_skip < 0) {
                        if (allcount > req_limit) {
                          req_skip =
                            Math.floor(allcount / req_limit) * req_limit;
                        } else {
                          req_skip = 0;
                        }
                      }
                      dbo
                        .collection("logicalScenario")
                        .find(lg_filter, { skip: req_skip, limit: req_limit })
                        .toArray(function (err, lg_result) {
                          if (err) throw err;
                          if (lg_result === null) {
                            logicalList = { allcount: 0 };
                            logicalList.result = [];
                            res.json(logicalList);
                          } else {
                            logicalList = { allcount: allcount };
                            logicalList.result = lg_result;
                            res.json(logicalList);
                          }
                        });
                    }
                  });
              }
            });
        }
      }
    }
  );
});

app.get("/scenario_editor/api/getConcreteListByRange", (req, res) => {
  let concreateList = [];
  let req_skip = Number(req.query["skip"]);
  let req_limit = Number(req.query["limit"]);
  let req_filter_lsid = "";
  let ref_id = "";

  for (const key in req.query) {
    switch (key) {
      case "lsid":
        req_filter_lsid = req.query[key];
        break;
    }
  }

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      if (req_filter_lsid !== undefined && req_filter_lsid !== "") {
        dbo
          .collection("logicalScenario")
          .findOne({ gid: req_filter_lsid }, function (err, lg_result) {
            if (err) throw err;
            if (lg_result !== null) {
              ref_id = lg_result["_id"];
              let title_reg_filter = { logic_ref: ref_id };
              dbo
                .collection("concreteScenario")
                .find(title_reg_filter)
                .count(function (err, allcount) {
                  if (err) throw err;
                  if (allcount === null || allcount === 0) {
                    concreateList = { allcount: 0 };
                    concreateList.result = [];
                    res.json(concreateList);
                  } else {
                    if (req_skip < 0) {
                      if (allcount > req_limit) {
                        req_skip = Math.floor(allcount / req_limit) * req_limit;
                      } else {
                        req_skip = 0;
                      }
                    }
                    dbo
                      .collection("concreteScenario")
                      .find(title_reg_filter, {
                        skip: req_skip,
                        limit: req_limit,
                      })
                      .toArray(function (err, cn_result) {
                        if (err) throw err;
                        if (cn_result === null) {
                          concreateList = { allcount: 0 };
                          concreateList.result = [];
                          res.json(concreateList);
                        } else {
                          concreateList = { allcount: allcount };
                          concreateList.result = cn_result;
                          res.json(concreateList);
                        }
                      });
                  }
                });
            } else {
              concreateList = { allcount: 0 };
              concreateList.result = [];
              res.json(concreateList);
            }
          });
      } else {
        dbo
          .collection("concreteScenario")
          .find({})
          .count(function (err, allcount) {
            if (err) throw err;
            if (allcount === null) {
              concreateList = { allcount: 0 };
              concreateList.result = [];
              res.json(concreateList);
            } else {
              if (req_skip < 0) {
                if (allcount > req_limit) {
                  req_skip = Math.floor(allcount / req_limit) * req_limit;
                } else {
                  req_skip = 0;
                }
              }
              dbo
                .collection("concreteScenario")
                .find({}, { skip: req_skip, limit: req_limit })
                .toArray(function (err, cn_result) {
                  if (err) throw err;
                  if (cn_result === null) {
                    concreateList = { allcount: 0 };
                    concreateList.result = [];
                    res.json(concreateList);
                  } else {
                    concreateList = { allcount: allcount };
                    concreateList.result = cn_result;
                    res.json(concreateList);
                  }
                });
            }
          });
      }
    }
  );
});

const sleep = (ms) => {
  return new Promise((resolve) => setTimeout(resolve, ms));
};

/**
 * Functional Scenario 削除処理
 */
app.post("/scenario_editor/api/deleteFunctionalScenario", (req, res) => {
  let data = req.body;
  var id = "";
  for (const key in data) {
    id = key;
  }

  updateLogicalScenario(id);

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo
        .collection("functionalScenario")
        // eslint-disable-next-line no-unused-vars
        .deleteOne({ _id: new ObjectID(id) }, function (err, response) {
          if (err) throw err;
          sleep(100);
          res.json(id);
        });
    }
  );
});

app.post("/scenario_editor/api/deleteLogicalScenario", (req, res) => {
  let data = req.body;
  var id = "";
  for (const key in data) {
    id = key;
  }

  updateConcreteScenario(id);

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo
        .collection("logicalScenario")
        // eslint-disable-next-line no-unused-vars
        .deleteOne({ _id: new ObjectID(id) }, function (err, response) {
          if (err) throw err;
          res.json(id);
        });
    }
  );
});

app.post("/scenario_editor/api/deleteConcreteScenario", (req, res) => {
  let data = req.body;
  var id = "";
  for (const key in data) {
    id = key;
  }
  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo
        .collection("concreteScenario")
        // eslint-disable-next-line no-unused-vars
        .deleteOne({ _id: new ObjectID(id) }, function (err, response) {
          if (err) throw err;
          res.json(id);
        });
    }
  );
});

const updateLogicalScenario = (id) => {
  var logicalList = [];

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo
        .collection("logicalScenario")
        .find({ func_ref: new ObjectID(id) })
        .toArray(function (err, result) {
          if (err) throw err;
          logicalList = result;
          if (logicalList.length > 0) {
            for (var j = 0; j < logicalList.length; j++) {
              var myquery = { _id: new ObjectID(logicalList[j]._id) };
              var newvalues = { $set: { func_ref: null } };
              dbo.collection("logicalScenario").updateOne(
                myquery,
                newvalues,
                { upsert: true },
                // eslint-disable-next-line no-unused-vars
                async function (err, response) {
                  if (err) throw err;
                }
              );
            }
          }
        });
    }
  );
};

const updateConcreteScenario = (id) => {
  var concreteList = [];

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo
        .collection("concreteScenario")
        .find({ logic_ref: new ObjectID(id) })
        .toArray(function (err, result) {
          if (err) throw err;
          concreteList = result;
          if (concreteList.length > 0) {
            for (var j = 0; j < concreteList.length; j++) {
              var myquery = { _id: new ObjectID(concreteList[j]._id) };
              var newvalues = { $set: { logic_ref: null } };
              dbo.collection("concreteScenario").updateOne(
                myquery,
                newvalues,
                { upsert: true },
                // eslint-disable-next-line no-unused-vars
                async function (err, response) {
                  if (err) throw err;
                }
              );
            }
          }
        });
    }
  );
};

/**
 * Functional Scenario Editor Saveメニュー押下により実行されます。
 *
 * <Parameter>
 * id - Functional Scenario の ObjectID
 * gid - Functional Scenario の GID
 */
app.post("/scenario_editor/api/updateFunctionalScenario", (req, res) => {
  let scenarioData = req.body;

  let id = "";
  let gardenid = "";
  for (const key in req.query) {
    if (key === "id") id = req.query[key];
    if (key === "gid") gardenid = req.query[key];
  }

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    async function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      var myquery = { _id: new ObjectID(id.trim()) };
      var newvalues = { $set: { gid: gardenid, data: scenarioData } };
      dbo
        .collection("functionalScenario")
        .updateOne(
          myquery,
          newvalues,
          { upsert: true },
          async function (err, response) {
            if (err) throw err;
            const savedId = [{ id: response.insertedId }];
            res.send(savedId);
            db.close();
          }
        );
    }
  );
});

/**
 * Logical Scenario Editor Saveメニュー押下により実行されます。
 *
 * <Parameter>
 * id - Logical Scenario の ObjectID
 * gid - Logical Scenario の GID
 */
app.post("/scenario_editor/api/updateLogicalScenario", (req, res) => {
  let scenarioData = req.body;

  let id = "";
  let gardenid = "";
  for (const key in req.query) {
    if (key === "id") id = req.query[key];
    if (key === "gid") gardenid = req.query[key];
  }

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    async function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      var myquery = { _id: new ObjectID(id.trim()) };
      var newvalues = { $set: { gid: gardenid, data: scenarioData } };
      dbo
        .collection("logicalScenario")
        .updateOne(
          myquery,
          newvalues,
          { upsert: true },
          async function (err, response) {
            if (err) throw err;
            const savedId = [{ id: response.insertedId }];
            res.send(savedId);
            db.close();
          }
        );
    }
  );
});

/**
 * Concrete Scenario Editor Saveメニュー押下により実行されます。
 *
 * <Parameter>
 * id - Concrete Scenario の ObjectID
 * gid - Concrete Scenario の GID
 */
app.post("/scenario_editor/api/updateConcreteScenario", (req, res) => {
  let scenarioData = req.body;

  let id = "";
  let gardenid = "";
  for (const key in req.query) {
    if (key === "id") id = req.query[key];
    if (key === "gid") gardenid = req.query[key];
  }

  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    async function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      var myquery = { _id: new ObjectID(id.trim()) };
      var newvalues = { $set: { gid: gardenid, data: scenarioData } };
      dbo
        .collection("concreteScenario")
        .updateOne(
          myquery,
          newvalues,
          { upsert: true },
          async function (err, response) {
            if (err) throw err;
            const savedId = [{ id: response.insertedId }];
            res.send(savedId);
            db.close();
          }
        );
    }
  );
});

/**
 * Functional Scenario 登録処理
 *
 * <Parameter>
 * fs_gid - Functional Scenario の GID
 */
app.post("/scenario_editor/api/saveFunctionalScenario", (req, res) => {
  let scenarioData = req.body;
  var fs_gid = req.query["fs_gid"];
  if (!fs_gid) {
    fs_gid = null;
  }
  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo
        .collection("functionalScenario")
        .insertOne(
          { gid: fs_gid, data: scenarioData },
          function (err, response) {
            if (err) throw err;
            const savedId = [
              {
                id: response.insertedId,
              },
            ];
            res.send(savedId);
          }
        );
    }
  );
});

/**
 * Logical Scenario 登録処理
 *
 * <Parameter>
 * fs_id - Functional Scenario の ObjectID
 * ls_gid - Logical Scenario の GID
 */
app.post("/scenario_editor/api/saveLogicalScenario", (req, res) => {
  let scenarioData = req.body;
  var fs_id = req.query["fs_id"];
  var ls_gid = req.query["ls_gid"];
  if (!fs_id) {
    fs_id = null;
  }
  if (!ls_gid) {
    ls_gid = null;
  }
  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo.collection("logicalScenario").insertOne(
        {
          gid: ls_gid,
          func_ref: new ObjectID(fs_id),
          used_date: "",
          data: scenarioData,
        },
        function (err, response) {
          if (err) throw err;
          const savedId = response.ops[0]._id;
          res.send(savedId);
        }
      );
    }
  );
});

/**
 * Concrete Scenario 登録処理
 *
 * <Parameter>
 * ls_id - Logical Scenario の ObjectID
 * cs_gid - Concrete Scenario の GID
 */
app.post("/scenario_editor/api/saveConcreteScenario", (req, res) => {
  let scenarioData = req.body;
  var ls_id = req.query["ls_id"];
  var cs_gid = req.query["cs_gid"];
  if (!ls_id) {
    ls_id = null;
  }
  if (!cs_gid) {
    cs_gid = null;
  }
  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo.collection("concreteScenario").insertOne(
        {
          gid: cs_gid,
          logic_ref: new ObjectID(ls_id),
          parameter_ref: null,
          used_date: "",
          parameter_index: -1,
          data: scenarioData,
        },
        function (err, response) {
          if (err) throw err;
          const savedId = response.ops[0]._id;
          res.send(savedId);
        }
      );
    }
  );
});

const getDateString = () => {
  const date = new Date();
  const Y = date.getFullYear();
  const M = ("00" + (date.getMonth() + 1)).slice(-2);
  const D = ("00" + date.getDate()).slice(-2);
  const h = ("00" + date.getHours()).slice(-2);
  const m = ("00" + date.getMinutes()).slice(-2);
  const s = ("00" + date.getSeconds()).slice(-2);
  return Y + M + D + h + m + s;
};

app.get("/scenario_editor/api/getParameterListById", (req, res) => {
  let logicalId = "";
  for (const key in req.query) {
    if (key === "id") logicalId = req.query[key];
  }
  MongoClient.connect(
    "mongodb://localhost:27017/",
    { useNewUrlParser: true, useUnifiedTopology: true },
    function (err, db) {
      if (err) throw err;
      var dbo = db.db("test");
      dbo
        .collection("parameterList")
        .findOne(
          { logical_ref: new ObjectID(logicalId) },
          function (err, result) {
            if (err) throw err;
            res.json(result);
          }
        );
    }
  );
});

app.post("/scenario_editor/api/autoGenerateConcreteScenario", (req, res) => {
  let bodyData = req.body;
  let logicalId = "";
  let used_date = "";
  let logicalGid = "";
  let paramId = "";
  let concreteGid = "";
  for (const key in req.query) {
    if (key === "id") logicalId = req.query[key];
    if (key === "used_date")
      used_date = req.query[key] === "undefined" ? undefined : req.query[key];
    if (key === "concreteGid") concreteGid = req.query[key];
    if (key === "logicalGid") logicalGid = req.query[key];
  }

  if (
    logicalId === "" ||
    concreteGid === "" ||
    bodyData["parameterList"] === undefined ||
    bodyData["parameterList"].length < 1 ||
    bodyData["logicalScenario"] === undefined ||
    bodyData["parameterDefinition"] === undefined
  ) {
    res.status(400);
    return;
  }
  const transaction = async () => {
    let logicalScenario = JSON.parse(bodyData["logicalScenario"]);
    let parameterList = JSON.parse(bodyData["parameterList"]);
    let parameterDefinition = JSON.parse(bodyData["parameterDefinition"]);
    let dateString = getDateString();
    let client;
    let concreteData = [];
    try {
      client = await MongoClient.connect("mongodb://localhost:27017/", {
        useNewUrlParser: true,
        useUnifiedTopology: true,
      });

      const db = client.db("test");
      const logicalCollection = await db.collection("logicalScenario");
      const concreteCollection = await db.collection("concreteScenario");
      const parameterListCollection = await db.collection("parameterList");

      let logicalTemplate = JSON.stringify(logicalScenario)
        .replace(/"\s*<%/g, "<%")
        .replace(/%>\s*"/g, "%>");
      for (let i = 0; i < parameterList.length; i++) {
        if (parameterList[i]["rowUsed"] === true) {
          concreteData.push(
            JSON.parse(ejs.render(logicalTemplate, parameterList[i]))
          );
        }
      }

      if (used_date === undefined) {
        await logicalCollection.updateOne(
          {
            $and: [{ _id: new ObjectID(logicalId.trim()) }],
          },
          {
            $set: {
              gid: logicalGid,
              data: logicalScenario,
              used_date: dateString,
            },
          }
          // { session }
        );
      } else {
        if (used_date === "") {
          await logicalCollection.updateOne(
            {
              $and: [
                { _id: new ObjectID(logicalId.trim()) },
                { used_date: "" },
              ],
            },
            {
              $set: {
                gid: logicalGid,
                data: logicalScenario,
                used_date: dateString,
              },
            }
            // { session }
          );
        } else {
          let result = await logicalCollection.findOne({
            $and: [{ _id: new ObjectID(logicalId) }, { used_date: used_date }],
          });
          let insid = await logicalCollection.insertOne({
            gid: logicalGid,
            func_ref: result.func_ref,
            used_date: dateString,
            data: logicalScenario,
          });
          logicalId = insid.insertedId;
        }
      }

      let paramInsertResult = await parameterListCollection.insertOne({
        gid: concreteGid,
        used_date: dateString,
        logical_ref: new ObjectID(logicalId),
        param: parameterDefinition,
        data: parameterList,
      });
      paramId = paramInsertResult.insertedId;

      let insertConcreteList = [];
      for (let i = 0; i < parameterList.length; i++) {
        if (parameterList[i]["rowUsed"] === true) {
          let concreteRow = {};
          concreteRow.gid = concreteGid;
          concreteRow.used_date = dateString;
          concreteRow.logical_ref = new ObjectID(logicalId);
          concreteRow.parameter_ref = new ObjectID(paramId);
          concreteRow.parameter_index = i;
          concreteRow.data = concreteData[i];
          insertConcreteList.push(concreteRow);
        }
      }
      await concreteCollection.insertMany(insertConcreteList);
      res.json({ logicalId: logicalId });
    } catch (error) {
      console.log(error);
      res.status(500).send("");
    } finally {
      client.close();
    }
  };

  transaction();
});

const port = 5000;

app.listen(port, () => console.log("Server started on port " + port));
