package com.tsa.tsa.services;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.util.normalizer.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Processor {

    public String stopWordRegex = "\\b(TWO|THREE|FOUR|FIVE|SIX|SEVEN|EIGHT|NINE|B|C|D|E|F|G|H|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|ONE|SAY|A|ABOUT|ABOVE|AFTER|AGAIN|AGAINST|ALL|AM|AN|AND|ANY|ARE|AS|AT|BE|BECAUSE|BEEN|BEFORE|BEING|BELOW|BETWEEN|BOTH|BUT|BY|COULD|DID|DO|DOES|DOING|DOWN|DURING|EACH|FEW|FOR|FROM|FURTHER|HAD|HAS|HAVE|HAVING|HE|HE'D|HE'LL|HE'S|HER|HERE|HERE'S|HERS|HERSELF|HIM|HIMSELF|HIS|HOW|HOW'S|I|I'D|I'LL|I'M|I'VE|IF|IN|INTO|IS|IT|IT'S|ITS|ITSELF|LET'S|ME|MORE|MOST|MY|MYSELF|NOR|OF|ON|ONCE|ONLY|OR|OTHER|OUGHT|OUR|OURS|OURSELVES|OUT|OVER|OWN|SAME|SHE|SHE'D|SHE'LL|SHE'S|SHOULD|SO|SOME|SUCH|THAN|THAT|THAT'S|THE|THEIR|THEIRS|THEM|THEMSELVES|THEN|THERE|THERE'S|THESE|THEY|THEY'D|THEY'LL|THEY'RE|THEY'VE|THIS|THOSE|THROUGH|TO|TOO|UNDER|UNTIL|UP|VERY|W|WAS|WE|WE'D|WE'LL|WE'RE|WE'VE|WERE|WHAT|WHAT'S|WHEN|WHEN'S|WHERE|WHERE'S|WHICH|WHILE|WHO|WHO'S|WHOM|WHY|WHY'S|WITH|WOULD|YOU|YOU'D|YOU'LL|YOU'RE|YOU'VE|YOUR|YOURS|YOURSELF|YOURSELVES)\\b\\s?";



    public Processor() {

    }

    public String processTweet(CharSequence tweet) {
        try {
            tweet = new UrlCharSequenceNormalizer().normalize(tweet); // removes URLS
            tweet = new TwitterCharSequenceNormalizer().normalize(tweet); // removes Twitter related characters
            tweet = new ShrinkCharSequenceNormalizer().normalize(tweet); // removes excessive characters
            tweet = new NumberCharSequenceNormalizer().normalize(tweet); // removes numbers
            tweet = new EmojiCharSequenceNormalizer().normalize(tweet); // removes emojis
            tweet = new AggregateCharSequenceNormalizer().normalize(tweet); //removes ???
            tweet = tweet.toString().replaceAll("(['][\\w]+)", " "); //removes characters after an apostrophe
            tweet = tweet.toString().replaceAll("[^\\s\\w]", " "); //removes punctuation

            tweet = tweet.toString().toUpperCase().replaceAll(stopWordRegex, " ");
            tweet = tweet.toString().replaceAll("\\s+", " ");

            return tweet.toString();
        } catch (Exception exception) {
            System.out.println(exception + tweet.toString());
            return "";
        }
    }
}
