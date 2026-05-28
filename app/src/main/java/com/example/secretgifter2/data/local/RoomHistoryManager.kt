package com.example.secretgifter2.data.local

import android.content.Context
import com.example.secretgifter2.data.model.SavedRoom
import org.json.JSONArray
import org.json.JSONObject

class RoomHistoryManager(
    context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "room_history",
            Context.MODE_PRIVATE
        )

    fun saveRoom(room: SavedRoom) {

        val current = getRooms().toMutableList()

        val exists = current.any {
            it.roomId == room.roomId
        }

        if (!exists) {
            current.add(0, room)
        }

        val jsonArray = JSONArray()

        current.forEach {

            val obj = JSONObject()

            obj.put("roomId", it.roomId)
            obj.put("roomCode", it.roomCode)
            obj.put("finishedAt", it.finishedAt)

            jsonArray.put(obj)
        }

        prefs.edit()
            .putString(
                "rooms",
                jsonArray.toString()
            )
            .apply()
    }

    fun getRooms(): List<SavedRoom> {

        val json =
            prefs.getString("rooms", null)
                ?: return emptyList()

        val jsonArray = JSONArray(json)

        val result = mutableListOf<SavedRoom>()

        for (i in 0 until jsonArray.length()) {

            val obj = jsonArray.getJSONObject(i)

            result.add(
                SavedRoom(
                    roomId = obj.getInt("roomId"),
                    roomCode = obj.getString("roomCode"),
                    finishedAt = obj.getLong("finishedAt")
                )
            )
        }

        return result
    }
}