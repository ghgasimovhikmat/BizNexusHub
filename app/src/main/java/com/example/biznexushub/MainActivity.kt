
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.biznexushub

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizNexusApp()
        }
    }
}


    @Composable
    fun BizNexusApp() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "splash") {
            composable("splash") { SplashScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignUpScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("features") { FeaturesScreen(navController) }
            composable("post") { PostCollaborationScreen(navController) }
            composable("contact") { ContactScreen(navController) }
            composable("about") { AboutScreen(navController) }
            composable("dashboard") { DashboardScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
            composable("discover") { DiscoverScreen(navController) }
            composable("limited_offers") { LimitedOffersScreen(navController) }
            composable("special_offers") { SpecialOffersScreen(navController) }
            composable("offer_pro_starter") {
                OfferDetailScreen(
                    navController,
                    "Pro Starter Boost",
                    "Boost your journey with AI-driven networking, beta feature access, and a verified badge for credibility. Get discovered faster, connect easier, and start strong. Perfect for new freelancers and early-stage startups. Includes onboarding webinars and 24/7 support during your first month."
                )
            }
            composable("offer_collab_spotlight") {
                OfferDetailScreen(
                    navController,
                    "Collaboration Spotlight",
                    "Get your collaboration post featured on the homepage and top feeds. This offer guarantees maximum visibility to investors, freelancers, and startups. Includes 3-day analytics, engagement tracking, and profile boost. Limited slots per week."
                )
            }
            composable("offer_local_champion") {
                OfferDetailScreen(
                    navController,
                    "Local Champion Badge",
                    "Showcase your leadership in your city. This badge places your profile at the top of regional searches, helps build local trust, and includes an invite to exclusive local networking events and newsletters. Great for community builders."
                )
            }
            composable("offer_premium") {
                OfferDetailScreen(
                    navController,
                    "BizNexus Premium",
                    "Unlock the full potential of BizNexus Hub with Premium. Enjoy an ad-free interface, unlimited messaging, access to premium-only networking events, smart project matches, and advanced analytics. Designed for professionals who want serious growth."
                )
            }
            composable("offer_premium_2") {
                OfferDetailScreen(
                    navController,
                    "Premium 2.0",
                    "All the benefits of Premium, plus AI collaboration advisors, team-based project dashboards, smart deadline milestones, and an intelligent notification engine. This is the future of professional networking productivity."
                )
            }
            composable("offer_freelancer") {
                OfferDetailScreen(
                    navController,
                    "Freelancer Accelerator",
                    "Enhance your credibility with profile verification, client testimonials, and prioritized exposure to project owners. Includes 1-on-1 mentorship, skill badge assessments, and weekly feature slots. Tailored for solo professionals."
                )
            }
            composable("offer_startup") {
                OfferDetailScreen(
                    navController,
                    "Startup Visibility Pack",
                    "Stand out in a competitive market. Your startup profile gets homepage placement, top results in industry searches, and access to growth analytics tools. Also includes press release templates and promo visuals for your brand."
                )
            }
        }
    }

@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Welcome, [UserName]", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Role: Freelancer", color = Color.White, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Recent Collaborations:", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Text("- Collaboration with Jane Doe on AI project", modifier = Modifier.padding(8.dp))
            }
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Text("- Joined SME Startup pitch team", modifier = Modifier.padding(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("profile") }, modifier = Modifier.fillMaxWidth()) {
                Text("View My Profile")
            }
            Button(onClick = { navController.navigate("discover") }, modifier = Modifier.fillMaxWidth()) {
                Text("Discover Collaborators")
            }
            Button(onClick = { navController.navigate("post") }, modifier = Modifier.fillMaxWidth()) {
                Text("New Collaboration")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true } // Clear backstack
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log Out", color = Color.White)
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(16.dp))

            // Name input
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Description / Bio input
            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Bio / Description", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Skills input
            OutlinedTextField(
                value = skills,
                onValueChange = { skills = it },
                label = { Text("Skills", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Experience input
            OutlinedTextField(
                value = experience,
                onValueChange = { experience = it },
                label = { Text("Experience Summary", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Save logic */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Save Profile")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("dashboard") }, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Dashboard")
            }
        }
    }
}


@Composable
fun DiscoverScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Discover", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))

            // Search input with white text
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Trending Tags: #AI, #Startup, #Mentor", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Jane Doe - Startup Founder")
                    Text("Looking for AI developers")
                    Button(onClick = { /* Connect */ }) {
                        Text("Connect")
                    }
                }
            }
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("John Smith - Mentor")
                    Text("Offering guidance on MVP building")
                    Button(onClick = { /* Connect */ }) {
                        Text("Connect")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("dashboard") }, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Dashboard")
            }
        }
    }
}



@Composable
fun LogoHeader() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = Modifier.size(100.dp)
    )
}

@Composable
fun SplashScreen(navController: NavHostController) {
    // Use a LaunchedEffect to delay for 2 seconds, then navigate to login
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true } // remove splash from backstack
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LogoHeader()
            Spacer(modifier = Modifier.height(16.dp))
            Text("BizNexus Hub", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeader()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Login", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )

        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Don't have an account? Sign up",
            modifier = Modifier.clickable { navController.navigate("signup") },
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                "Continue to Offers →",
                color = Color.Yellow,
                modifier = Modifier.clickable { navController.navigate("limited_offers") }
            )
        }
    }
}

@Composable
fun SignUpScreen(navController: NavHostController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text("Sign Up", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = role, onValueChange = { role = it }, label = { Text("Role") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Toast.makeText(context, "Registered!", Toast.LENGTH_SHORT).show()
                navController.navigate("home") // <-- Navigate to home after signup
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(" Limited Time Offers", color = Color.Yellow, modifier = Modifier.clickable {
            navController.navigate("limited_offers")
        })
        Spacer(modifier = Modifier.height(8.dp))
        Text(" Special Offers", color = Color.Yellow, modifier = Modifier.clickable {
            navController.navigate("special_offers")
        })
    }
}

@Composable
fun LimitedOffersScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Limited Time Offers", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_media_previous), contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black))
        },
        containerColor = Color.Black
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OfferItem("Pro Starter Boost", "Free 30-day AI networking", onClick = {
                navController.navigate("offer_pro_starter")
            })
            OfferItem("Collaboration Spotlight", "Feature your projects on top", onClick = {
                navController.navigate("offer_collab_spotlight")
            })
            OfferItem("Local Champion Badge", "Be seen in your local community", onClick = {
                navController.navigate("offer_local_champion")
            })
        }
    }
}

@Composable
fun SpecialOffersScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Special Offers", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_media_previous), contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black))
        },
        containerColor = Color.Black
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OfferItem("BizNexus Premium", "Ad-free, premium events", onClick = {
                navController.navigate("offer_premium")
            })
            OfferItem("Premium 2.0", "AI tools, team workspace", onClick = {
                navController.navigate("offer_premium_2")
            })
            OfferItem("Freelancer Accelerator", "Boost for freelancers", onClick = {
                navController.navigate("offer_freelancer")
            })
            OfferItem("Startup Visibility Pack", "Top homepage placement", onClick = {
                navController.navigate("offer_startup")
            })
        }
    }
}

@Composable
fun OfferItem(title: String, description: String, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable(onClick = onClick)) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
        Text(text = description, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun OfferDetailScreen(navController: NavHostController, title: String, description: String) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_media_previous),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = description, color = Color.White, fontSize = 16.sp, lineHeight = 24.sp)
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }
                Button(onClick = {
                    Toast.makeText(context, "Purchase Success!", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Purchase")
                }
            }
        }
    }
}



@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("About", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LogoHeader()
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "About BizNexus Hub",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "BizNexus Hub is designed to bridge the gap between freelancers, startups, and SMEs by offering a smart collaboration platform.\n\nWe provide tools for networking, matching with the right partners, and discovering projects or talent seamlessly.",
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { navController.navigate("features") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Get More", color = Color.White)
                }
            }
        }
    }
}




@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Welcome to BizNexus Hub", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Your go-to platform for smart networking, collaboration, and growth.",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate("dashboard") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Dashboard", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("My Profile", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("discover") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Discover", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("features") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Features", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("post") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Post Collaboration", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("contact") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Contact Us", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("about") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("About", color = Color.White)
            }
        }
    }
}



@Composable
fun FeaturesScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Features", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Platform Features",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(24.dp))
            FeatureItem("Smart Networking Suggestions", R.drawable.smart)
            FeatureItem("Freelance & Business Matching", R.drawable.matching)
            FeatureItem("Project Collaboration Tools", R.drawable.collaboration)
            FeatureItem("Event Discovery", R.drawable.event)
            FeatureItem("Verified Profiles", R.drawable.verify)
        }
    }
}

@Composable
fun FeatureItem(text: String, imageRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = text,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, color = Color.White, fontSize = 18.sp)
    }
}




@Composable
fun PostCollaborationScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Post Collaboration", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = details, onValueChange = { details = it }, label = { Text("Details") },
                modifier = Modifier.fillMaxWidth().height(120.dp), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* handle submit */ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                Text("Submit Post", color = Color.White)
            }
        }
    }
}



@Composable
fun ContactScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subscribe by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Contact", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Contact Us", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = subscribe, onCheckedChange = { subscribe = it })
                Text("Subscribe to our updates", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* send message */ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                Text("Submit", color = Color.White)
            }
        }

    }
}

