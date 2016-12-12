package com.mobyware.sqsClient;

//References
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
 
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;


public class AmazonQueueClient{
    public static void main(String[] args){
        String queueURL = "https://sqs.us-east-1.amazonaws.com/297438001899/3PL-DEV-PicklistOrderMgmt-Queue";
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Can't load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (~/.aws/credentials), and is a in valid format.",
                    e);
        }

        AmazonSQSClient sqs = new AmazonSQSClient(credentials);

        //Receive Message
        System.out.println("Trying to receive message");

        ReceiveMessageRequest receiveMessage = new ReceiveMessageRequest(queueURL);
        List<Message> messages = sqs.receiveMessage(receiveMessage).getMessages();
        for (Message message : messages) {
            System.out.println("  Message");
            System.out.println("    MessageId:     " + message.getMessageId());
            System.out.println("    Body:          " + message.getBody());
            /*for (Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("  Attribute");
                System.out.println("    Name:  " + entry.getKey());
                System.out.println("    Value: " + entry.getValue());
            }*/
        }
    }

    private static void printMessage(String message){
        System.out.println("Amazon says: " + message);
    }
}