package com.android.academy.details.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.academy.R
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val facts = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        facts.add("Captain America is confirmed to have a much larger role in Avengers 4.")
        facts.add("When Thanos hurls the moon on Titan, he uses two Infinity Stones to do so: the Power Stone and Space Stone")
        facts.add("Valkyrie and several other people escaped the Asgardian ship before Thanos starts wreaking havoc. Sadly, we still don’t know the fate of Korg, but my hunch is he’ll make a return in Avengers 4.")
        facts.add("Kenneth Branagh, who directed the first Thor, does the voice for the distress call in the very beginning.")
        facts.add("One of the early drafts of the script saw Thanos collect the Power Stone from Xandar, but the directors ultimately decided not to show him collecting each stone because it would be too repetitive.")
        facts.add("Speaking of Xandar, Gamora was supposed to encounter Thanos there, but they eventually scrapped that and instead changed the location to Knowhere.")
        facts.add("The directors included a lot of nods to Raiders of the Lost Ark, including a scene that sees Maw burn his hand when he reaches for Doctor Strange’s Time Stone.")
        facts.add("Early drafts included backstory for the Black Order, but they were eventually cut because it would have made the movie too bloated.")
        facts.add("The directors wanted to save Captain America until the battle on Wakanda but ultimately decided to have him appear in Scotland.")
        facts.add("In order to explain what each Infinity Stone did, the directors made sure to show Thanos using them soon after he got a new one.")
        facts.add("When we see Thanos turn Drax and Mantis into weird shapes, it was a nod to the Jim Starlin Infinity Gauntlet comics.")
        facts.add("The directors weren’t sure about setting the final battle in Wakanda. But after the success of Black Panther, they knew they made the right choice.")
        facts.add("The directors couldn’t figure out how to make Tony Stark beat Ebony Maw aboard the spaceship, so they looked to Raiders of the Lost Ark again as inspiration. Stark eventually wins by blowing a hole in the side of the spaceship, which sucks Maw out.")
        facts.add("Nebula getting caught essentially allows Thanos to successfully obtain all the Infinity Stones, because she reveals to the Mad Titan that Gamora knew where the Soul Stone was.")
        facts.add("The directors were initially going to show Thor’s new weapon, Stormbreaker, being forged by dragons. But they eventually settled on Eitri.")
        facts.add("The role of Red Skull was necessary because the directors needed someone to explain how to obtain the Soul Stone.")
        facts.add("Early drafts of the script included more of Thanos’ backstory, but the scenes were cut.")
    }

    fun moveToYoutubeLink(){
        val move_to_link = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6ZfuNTqbHE8"))
        move_to_link.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        move_to_link.setPackage("com.google.android.youtube")
        startActivity(move_to_link)

    }

    fun popFunFact(){
        val factIdx = Random.nextInt(0,facts.size)
        val toastMassage = Toast.makeText(applicationContext,facts[factIdx],(Toast.LENGTH_LONG*2))
        toastMassage.show()

    }
}
