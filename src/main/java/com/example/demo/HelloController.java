package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
public class HelloController
{
    private static int id = 0;
    private int count;

    @RequestMapping(value="/getHighestGreeting",method=RequestMethod.POST)
    public Greeting getHighestGreeting(@RequestBody List<Greeting> list)
    {
        int maxId = 0;
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getId() > list.get(maxId).getId())
            {
                maxId = i;
            }
        }
        return list.get(maxId);
    }

    @RequestMapping(value="/newgreeting", method=RequestMethod.POST)
    public Greeting newGreeting()
    {
        Greeting greeting = new Greeting(1, "Hello");
        return greeting;
    }

    @RequestMapping(value="/updateGreeting", method = RequestMethod.PUT)
    public Greeting updateGreeting(@RequestBody String newMessage) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        String message = FileUtils.readFileToString(new File("src/message.txt"), CharEncoding.UTF_8);

        Greeting greeting = mapper.readValue(message, Greeting.class);

        greeting.setContent(newMessage);

        mapper.writeValue(new File("src/message.txt"), greeting);

        return greeting;
    }

    @RequestMapping(value="/deleteGreeting", method=RequestMethod.DELETE)
    public void deleteGreeting(@RequestBody int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String message = FileUtils.readFileToString(new File("src/message.txt"), CharEncoding.UTF_8);
        Greeting greeting = mapper.readValue(message, Greeting.class);
        if(greeting.getId() == id) {
            FileUtils.writeStringToFile(new File("src/message.txt"), "", CharEncoding.UTF_8);
        }
    }

    @RequestMapping(value="/createGreeting", method=RequestMethod.POST)
    public Greeting createGreeting(@RequestBody String name) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        Greeting newGreeting = new Greeting(count++, name);
        mapper.writeValue(new File("src/message.txt"), newGreeting);
        return newGreeting;
    }

    @RequestMapping(value = "/sameGBye World!reeting", method = RequestMethod.POST)
    public Greeting sameGreeting(@RequestBody Greeting greeting)
    {
        return greeting;

    }

    @RequestMapping(value="/greeting", method= RequestMethod.GET)
    public Greeting greeting() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        String message = FileUtils.readFileToString(new File("src/message.txt"), CharEncoding.UTF_8);

        Greeting greeting = mapper.readValue(message, Greeting.class);

        return greeting;
    }
//    @RequestMapping("/greeting")
//    public Greeting greeting() {
//        id++;
//        return new Greeting(id, "Hello");
//    }

    @RequestMapping("/greetingByName")
    public String greetingByName(@RequestParam(value="name", defaultValue="World") String name)
    {
        return "Hello " + name;
    }
}