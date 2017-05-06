package jp.ne.khddmks

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.{DynamoDB}
import com.amazonaws.services.lambda.runtime.Context

/**
  * Created by khdd-mks on 2017/05/02.
  */
class Main {
  def handler(param: String, context: Context): String = {
    def createDynamoDB = {
      val dbClient = AmazonDynamoDBClientBuilder.standard
        .withRegion(Regions.AP_NORTHEAST_1)
        .build
      val db = new DynamoDB(dbClient)
      db
    }
    def readFromDynamoDb(db: DynamoDB) = {
      val table = db.getTable("sample001")
      val json = table.getItem("key", "config").getJSON("values")
      json
    }

    readFromDynamoDb(createDynamoDB)
  }
}
