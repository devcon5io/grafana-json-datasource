package io.devcon5.metrics;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import rest.x.Args;

/**
 *
 */
public class Configuration {

    @Inject
    private Instance<Args> args;
    private CommandLine commandLine;

    @PostConstruct
    public void init(){

        if(!args.isUnsatisfied()){
            this.commandLine = CLI.create("default").parse(args.get().getArgs());
        }
    }

    public CommandLine getCommandLine(){
        return this.commandLine;
    }

}
